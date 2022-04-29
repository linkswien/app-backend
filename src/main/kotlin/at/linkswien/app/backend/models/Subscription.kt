package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id

data class Subscription(
    @Id val userId: Int,
    @Id val eventId: Int
)