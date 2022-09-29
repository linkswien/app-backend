package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.entities.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository: JpaRepository<Event, Int>