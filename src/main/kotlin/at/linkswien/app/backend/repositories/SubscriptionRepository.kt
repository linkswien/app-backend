package at.linkswien.app.backend.repositories

import at.linkswien.app.backend.models.Subscription
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface SubscriptionRepository: ReactiveCrudRepository<Subscription, Int>