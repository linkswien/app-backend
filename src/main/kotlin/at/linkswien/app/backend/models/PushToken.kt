package at.linkswien.app.backend.models

import javax.persistence.Id
import java.time.Instant
import javax.persistence.Entity

@Entity
data class PushToken(
    @Id val id: Int,
    val userId: Int,
    val token: String,
    val expirationTs: Instant
)