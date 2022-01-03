package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("HelloMessage")
data class HelloMessage(
    @Id val id: Int? = null,
    val message: String,
)
