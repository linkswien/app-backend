package at.linkswien.app.backend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@EnableWebFluxSecurity
@Configuration
class JWTSecurityConfig {
    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {

        http.authorizeExchange()
            .pathMatchers("/login-redirect", "/api/v1/login").permitAll()
            .anyExchange().authenticated()
            .and()
            .csrf { x -> x.disable()}
            .cors().configurationSource(createCorsConfigSource())
            .and()
            .oauth2ResourceServer()
            .jwt()
        return http.build()
    }

    private fun createCorsConfigSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowedOrigins = listOf("http://localhost:8080", "http://localhost:8081")
        config.allowedMethods = listOf("*")

        source.registerCorsConfiguration("/**", config)
        return source
    }
}