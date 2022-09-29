package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.entities.PushToken
import org.springframework.data.jpa.repository.JpaRepository

interface PushTokenRepository: JpaRepository<PushToken, Int>