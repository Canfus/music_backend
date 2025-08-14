package dev.music.auth.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class Security {

  @Bean
  fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}