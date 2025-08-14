package dev.music.auth.apps.sign_up.gprc

import io.grpc.Status
import io.grpc.StatusException
import net.devh.boot.grpc.server.service.GrpcService

import dev.music.auth.SignUpRequest
import dev.music.auth.SignUpResponse
import dev.music.auth.AuthServiceGrpcKt
import dev.music.auth.apps.sign_up.usecases.SignUpUseCase
import dev.music.auth.common.exceptions.ApplicationException

@GrpcService
class SignUpGrpcAdapterImpl(
  private val signUp: SignUpUseCase
) : AuthServiceGrpcKt.AuthServiceCoroutineImplBase() {

  override suspend fun signUp(request: SignUpRequest): SignUpResponse {
    try {
      return signUp.execute(request)
    } catch (e: ApplicationException) {
      throw StatusException(Status.INTERNAL.withDescription(e.message))
    }
  }
}