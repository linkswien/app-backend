package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.models.EventType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface EventTypeRepository: JpaRepository<EventType, Int>