package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.models.PushToken
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface PushTokenRepository: ReactiveCrudRepository<PushToken, Int>