package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import java.time.Instant

data class PushToken(
    @Id val id: Int,
    val userId: Int,
    val token: String,
    val expirationTs: Instant
)