package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.models.HelloMessage
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface HelloRepository: ReactiveCrudRepository<HelloMessage, Int>