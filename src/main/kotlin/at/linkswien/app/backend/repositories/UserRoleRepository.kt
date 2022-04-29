package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.models.UserRole
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRoleRepository: ReactiveCrudRepository<UserRole, Int>