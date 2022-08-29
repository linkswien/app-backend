package at.linkswien.app.backend

import at.linkswien.app.backend.models.Event
import at.linkswien.app.backend.models.EventType
import at.linkswien.app.backend.repositories.EventRepository
import at.linkswien.app.backend.repositories.EventTypeRepository
import at.linkswien.app.backend.repositories.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(
    private val eventTypeRepo: EventTypeRepository,
    private val userRepo: UserRepository,
    private val eventRepo: EventRepository
) {

    @GetMapping("api/v1/events")
    fun getEvents(): List<Event> = eventRepo.findAll()

}