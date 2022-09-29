package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.entities.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository: JpaRepository<Notification, Int>