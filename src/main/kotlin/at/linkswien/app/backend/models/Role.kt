package at.linkswien.app.backend.models

import javax.persistence.Id
import javax.persistence.Entity

@Entity
data class Role(
    @Id val id: Int,
    val name: String,
    val description: String
)