package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("subscription")
data class Subscription(
    @Id val userId: Int,
    @Id val eventId: Int
)