package dev.music.gateway.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
class SecurityConf {

  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    http.csrf { it.disable() }
      .authorizeHttpRequests {
        it
          .requestMatchers(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api/auth/**"
          ).permitAll()
          .anyRequest().authenticated()
      }

    return http.build()
  }
}