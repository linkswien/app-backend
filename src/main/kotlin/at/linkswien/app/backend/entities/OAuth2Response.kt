package at.linkswien.app.backend.entities

data class OAuth2Response(
    val access_token: String,
    val expires_in: Long,
    val refresh_expires_in: Long,
    val refresh_token: String,
    val token_type: String,
    val scope: String
)