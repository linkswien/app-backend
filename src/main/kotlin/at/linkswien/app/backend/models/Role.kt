package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id

data class Role(
    @Id val id: Int,
    val name: String,
    val description: String
)