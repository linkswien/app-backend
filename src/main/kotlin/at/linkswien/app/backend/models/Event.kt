package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("event")
data class Event(
    @Id val id: Int,
    val typeId: Int,
    val userId: Int,
    val creationTs: Instant,
    val title: String,
    val headerImg: String,
    val description: String,
    val typeData: String
)