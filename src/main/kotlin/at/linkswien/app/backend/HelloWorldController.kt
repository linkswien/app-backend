package at.linkswien.app.backend

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloWorldController {

    @GetMapping("hello-world")
    @ResponseBody
    suspend fun helloWorld() = "Hello world!"
}