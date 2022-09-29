package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.entities.EventType
import org.springframework.data.jpa.repository.JpaRepository

interface EventTypeRepository: JpaRepository<EventType, Int>