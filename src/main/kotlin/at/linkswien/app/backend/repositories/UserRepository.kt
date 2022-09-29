package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}