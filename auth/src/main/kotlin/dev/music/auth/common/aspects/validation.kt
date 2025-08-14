package dev.music.auth.common.aspects

import io.grpc.Status
import kotlin.reflect.KClass
import io.grpc.StatusException
import jakarta.validation.Validator
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.ProceedingJoinPoint
import kotlin.reflect.full.primaryConstructor
import org.springframework.stereotype.Component

import dev.music.auth.common.annotations.Validate

@Aspect
@Component
class Validation(
  private val validator: Validator
) {

  @Around("@annotation(validate)")
  fun validate(joinPoint: ProceedingJoinPoint, validate: Validate): Any? {
    val dtoClass = validate.dto
    val validationClass = validate.validator

    val dtoArg = joinPoint.args.firstOrNull { dtoClass.java.isInstance(it) }
      ?: throw StatusException(Status.INVALID_ARGUMENT.withDescription("DTO для валидации не найден"))

    val validationDto = mapToValidationClass(dtoArg, validationClass)
      ?: throw StatusException(Status.INVALID_ARGUMENT.withDescription("Ошибка маппинга DTO"))

    val violations = validator.validate(validationDto)
    if (violations.isNotEmpty()) {
      val message = violations.joinToString("; ") { it.message }
      throw StatusException(Status.INVALID_ARGUMENT.withDescription(message))
    }

    return joinPoint.proceed()
  }

  private fun mapToValidationClass(source: Any, targetClass: KClass<*>): Any? {
    val ctor = targetClass.primaryConstructor ?: return null
    val params = ctor.parameters.associateWith { param ->
      source::class.members.firstOrNull { it.name == param.name }?.call(source)
    }
    return try {
      ctor.callBy(params)
    } catch (e: Exception) {
      null
    }
  }
}