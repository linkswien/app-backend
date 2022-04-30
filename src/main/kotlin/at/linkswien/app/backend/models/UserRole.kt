package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("userRole")
data class UserRole(
    @Id val userId: Int,
    @Id val roleId: Int,
)