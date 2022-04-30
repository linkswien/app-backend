package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("eventType")
data class EventType(
    @Id val id: Int,
    val name: String,
    val dataSchema: String,
    val iconUrl: String
)