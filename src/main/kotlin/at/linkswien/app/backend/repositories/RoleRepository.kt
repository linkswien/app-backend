package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.entities.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Int>