package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.models.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRepository: ReactiveCrudRepository<User, Int>