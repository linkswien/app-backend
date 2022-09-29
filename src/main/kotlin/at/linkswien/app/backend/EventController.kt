package at.linkswien.app.backend

import at.linkswien.app.backend.entities.Event
import at.linkswien.app.backend.entities.EventType
import at.linkswien.app.backend.entities.User
import at.linkswien.app.backend.models.CreateEvent
import at.linkswien.app.backend.repositories.EventRepository
import at.linkswien.app.backend.repositories.EventTypeRepository
import at.linkswien.app.backend.repositories.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class EventController(
    private val eventTypeRepo: EventTypeRepository,
    private val userRepo: UserRepository,
    private val eventRepo: EventRepository
) {

    @GetMapping("api/v1/events")
    fun getEvents(): List<Event> = eventRepo.findAll()

    @PutMapping("api/v1/events")
    fun addEvent(@RequestBody newEvent: CreateEvent): Event {

        val auth = SecurityContextHolder.getContext().authentication
        val claims = (auth.principal as Jwt).claims

        val username = claims["name"] as String
        val firstName = claims["given_name"] as String
        val lastName = claims["family_name"] as String
        val email = claims["email"] as String

        val existingUser = userRepo.findByEmail(email)

        val user = userRepo.save(
            User(
                id = existingUser?.id ?: -1,
                email = email,
                username = username,
                firstName = firstName,
                lastName = lastName,
                roles = emptyList(),
                eventSubscriptions = emptyList()
            )
        )

        return eventRepo.save(
            Event(
                id = -1,
                type = EventType(
                    id = 1,
                    name = "Demonstration",
                    dataSchema = "",
                    iconUrl = "flag"
                ),
                user = user,
                creationTs = Instant.now(),
                title = newEvent.title,
                headerImg = newEvent.headerImg,
                description = newEvent.description,
                typeData = newEvent.typeData
            )
        )
    }

}