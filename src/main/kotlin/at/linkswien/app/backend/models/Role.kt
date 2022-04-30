package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("role")
data class Role(
    @Id val id: Int,
    val name: String,
    val description: String
)