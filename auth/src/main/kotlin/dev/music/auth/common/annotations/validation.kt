package dev.music.auth.common.annotations

import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Validate(
  val dto: KClass<*>,
  val validator: KClass<*>
)
