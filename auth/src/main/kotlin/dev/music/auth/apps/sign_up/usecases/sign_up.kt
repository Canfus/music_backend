package dev.music.auth.apps.sign_up.usecases

import io.grpc.Status
import io.grpc.StatusException
import org.springframework.stereotype.Component

import dev.music.auth.SignUpRequest
import dev.music.auth.SignUpResponse
import dev.music.auth.common.annotations.Validate
import dev.music.auth.apps.sign_up.services.SignUpService
import dev.music.auth.common.exceptions.ApplicationException
import dev.music.auth.apps.sign_up.model.dto.SignUpRequestDto

interface SignUpUseCase {

  fun execute(dto: SignUpRequest): SignUpResponse
}

@Component
class SignUpUseCaseImpl(
  private val signUpService: SignUpService
) : SignUpUseCase {

  @Validate(dto = SignUpRequest::class, validator = SignUpRequestDto::class)
  override fun execute(dto: SignUpRequest): SignUpResponse {
    try {
      val user = signUpService.signUp(
        email = dto.email,
        username = dto.username.takeIf { it.isNotBlank() },
        rawPassword = dto.password,
      )

      return SignUpResponse.newBuilder()
        .setId(user.id)
        .setEmail(user.email)
        .setUsername(user.username)
        .build()
    } catch (exception: ApplicationException) {
      throw StatusException(Status.ALREADY_EXISTS.withDescription(exception.message))
    }
  }
}