package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.models.Notification
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface NotificationRepository: ReactiveCrudRepository<Notification, Int>