package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import java.time.Instant

data class Notification(
    @Id val id: Int,
    val creationTs: Instant,
    val eventId: Int,
    val creatorId: Int?,
    val recipientId: Int
)