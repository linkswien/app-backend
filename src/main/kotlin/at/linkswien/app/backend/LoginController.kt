package at.linkswien.app.backend

import at.linkswien.app.backend.config.BackendConfiguration
import at.linkswien.app.backend.models.OAuth2Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

@RestController
class LoginController(val config: BackendConfiguration) {

    private val client = WebClient.builder().build()

    @GetMapping("login-redirect")
    fun loginRedirect(@RequestParam redirectUri: String): ResponseEntity<String> {
        val uri = UriComponentsBuilder.fromUriString(config.authUri)
            .queryParam("response_type", "code")
            .queryParam("client_id", config.clientId)
            .queryParam("redirect_uri", redirectUri)
            .build()
            .toUri()

        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .location(uri)
            .build()
    }

   @PostMapping("api/v1/login")
    fun login(
        @RequestPart code: String,
        @RequestPart redirectUri: String
    ): ResponseEntity<OAuth2Response>? {
        return client.post()
            .uri(config.tokenUri)
            .headers { headers -> headers.setBasicAuth(config.clientId, config.clientSecret) }
            .body(
                BodyInserters.fromFormData("grant_type", "authorization_code")
                    .with("code", code)
                    .with("redirect_uri", redirectUri)
            )
            .retrieve()
            .onStatus(HttpStatus::isError) { response ->
                response.bodyToMono(String::class.java)
                    .flatMap { responseBody ->
                        Mono.error(
                            ResponseStatusException(response.statusCode(), responseBody)
                        )
                    }
            }
            .toEntity(OAuth2Response::class.java)
            .block()
    }
}
