package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("notification")
data class Notification(
    @Id val id: Int,
    val creationTs: Instant,
    val eventId: Int,
    val creatorId: Int?,
    val recipientId: Int
)