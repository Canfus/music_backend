package dev.music.gateway.apps.api.auth

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController {

  @Operation(summary = "Регистрация пользователя в приложении")
  @PostMapping("/sign-up")
  fun signUp() {}

  @Operation(summary = "Вход в систему")
  @PostMapping("/sign-in")
  fun signIn() {}

  @Operation(summary = "Выход из системы")
  @GetMapping("/sign-out")
  fun signOut() {}
}
