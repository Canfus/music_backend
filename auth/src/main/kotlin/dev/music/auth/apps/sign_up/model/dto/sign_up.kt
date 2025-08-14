package dev.music.auth.apps.sign_up.model.dto

import jakarta.validation.constraints.*

data class SignUpRequestDto(
  @field:NotBlank(message = "Почта не может быть пустой")
  @field:Email(message = "Почта указана некорректно")
  val email: String,

  @field:NotBlank(message = "Имя пользователя не может быть пустым")
  val username: String?,

  @field:Pattern(regexp = "[a-zA-Z0-9_]{8,}+", message = "Пароль не соответствует требованиям")
  val password: String,
)