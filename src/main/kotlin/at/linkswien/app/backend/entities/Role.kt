package at.linkswien.app.backend.entities

import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "role")
data class Role(
    @Id val id: Int,
    val name: String,
    val description: String
)