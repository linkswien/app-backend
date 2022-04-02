package at.linkswien.app.backend

import at.linkswien.app.backend.config.BackendConfiguration
import at.linkswien.app.backend.models.OAuth2Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriComponentsBuilder

@Controller
class LoginController(val config: BackendConfiguration) {

    private val client = WebClient.builder().build()

    @GetMapping("login-redirect")
    @ResponseBody
    suspend fun loginRedirect (@RequestParam redirectUri: String): ResponseEntity<String> {
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
    @ResponseBody
    suspend fun login(
        @RequestPart code: String,
        @RequestPart redirectUri: String
    ): OAuth2Response {
        return client.post()
            .uri(config.tokenUri)
            .headers{ headers -> headers.setBasicAuth(config.clientId, config.clientSecret)}
            .body(
                BodyInserters.fromFormData("grant_type", "authorization_code")
                    .with("code", code)
                    .with("redirect_uri", redirectUri)
            )
            .retrieve()
            .awaitBody()
    }
}
