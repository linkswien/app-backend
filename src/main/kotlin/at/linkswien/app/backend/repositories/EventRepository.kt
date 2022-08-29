package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.models.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface EventRepository: JpaRepository<Event, Int>