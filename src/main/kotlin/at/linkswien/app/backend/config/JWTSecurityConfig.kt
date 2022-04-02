package at.linkswien.app.backend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

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
            .oauth2ResourceServer()
            .jwt()
        return http.build()
    }
}