package at.linkswien.app.backend

import at.linkswien.app.backend.config.BackendConfiguration
import at.linkswien.app.backend.entities.OAuth2Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

@RestController
class LoginController(val config: BackendConfiguration) {

    private val client = WebClient.builder().build()

    @GetMapping("api/v1/login/redirect")
    fun loginRedirect(): ResponseEntity<String> {
        val uri = UriComponentsBuilder.fromUriString(config.authUri)
            .queryParam("response_type", "code")
            .queryParam("client_id", config.clientId)
            .queryParam("redirect_uri", config.callbackUri)
            .build()
            .toUri()

        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .location(uri)
            .build()
    }

    @GetMapping("api/v1/login/callback")
    fun login(
        @RequestParam code: String
    ): OAuth2Response? {
        return client.post()
            .uri(config.tokenUri)
            .headers { headers -> headers.setBasicAuth(config.clientId, config.clientSecret) }
            .body(
                BodyInserters.fromFormData("grant_type", "authorization_code")
                    .with("code", code)
                    .with("redirect_uri", config.callbackUri)
            )
            .exchangeToMono { response ->
                if (response.statusCode().is2xxSuccessful) {
                    response.bodyToMono(OAuth2Response::class.java);
                } else {
                    response.bodyToMono(String::class.java)
                        .flatMap { responseBody ->
                            Mono.error(
                                ResponseStatusException(response.statusCode(), responseBody)
                            )
                        }
                }
            }
            .block()
    }
}
