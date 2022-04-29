package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("user")
data class User(
    @Id val id: Int,
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String
)

