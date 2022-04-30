package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("pushToken")
data class PushToken(
    @Id val id: Int,
    val userId: Int,
    val token: String,
    val expirationTs: Instant
)