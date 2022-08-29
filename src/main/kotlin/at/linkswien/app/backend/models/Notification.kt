package at.linkswien.app.backend.models

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Notification(
    @Id val id: Int,
    val creationTs: Instant,
    val eventId: Int,
    val creatorId: Int?,
    val recipientId: Int
)