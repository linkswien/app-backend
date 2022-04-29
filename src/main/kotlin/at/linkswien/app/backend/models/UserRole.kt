package at.linkswien.app.backend.models

import org.springframework.data.annotation.Id

data class UserRole(
    @Id val userId: Int,
    @Id val roleId: Int,
)