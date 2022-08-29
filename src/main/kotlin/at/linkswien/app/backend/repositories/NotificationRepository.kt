package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.models.Notification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface NotificationRepository: JpaRepository<Notification, Int>