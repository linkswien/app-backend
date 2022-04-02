package at.linkswien.app.backend.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class BackendConfiguration(
    @Value("\${spring.security.oauth2.client.provider.linkslogin.authorization-uri}") val authUri: String,
    @Value("\${spring.security.oauth2.client.provider.linkslogin.token-uri}") val tokenUri: String,
    @Value("\${spring.security.oauth2.client.registration.linkslogin.clientId}") val clientId: String,
    @Value("\${spring.security.oauth2.client.registration.linkslogin.clientSecret}") val clientSecret: String
)