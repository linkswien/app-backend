package at.linkswien.app.backend.entities

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GenerationType
import javax.persistence.Table

@Entity
@Table(name = "eventType")
data class EventType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val dataSchema: String,
    val iconUrl: String
)