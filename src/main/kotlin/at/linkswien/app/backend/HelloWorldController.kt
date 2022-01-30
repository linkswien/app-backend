package at.linkswien.app.backend

import at.linkswien.app.backend.models.HelloMessage
import at.linkswien.app.backend.repositories.HelloRepository
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloWorldController(val repo: HelloRepository) {

    @PostMapping("hello-world")
    @ResponseBody
    suspend fun helloWorld (@RequestBody message: HelloMessage): HelloMessage {
        return repo.save(message).awaitFirst()
    }

    @GetMapping("hello-world")
    @ResponseBody
    suspend fun helloWorld() = repo.findById(1).awaitFirst()

    @GetMapping("/whoami")
    @ResponseBody
    suspend fun whoami(): String {
        val auth = ReactiveSecurityContextHolder.getContext().awaitFirst().authentication ?: return "not authenticated"

        return (auth.principal as DefaultOAuth2User).name
    }
}