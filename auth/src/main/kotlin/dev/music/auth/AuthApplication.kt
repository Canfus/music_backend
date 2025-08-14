package dev.music.auth

import dev.music.auth.config.env.EnvConf
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthApplication

fun main(args: Array<String>) {

  EnvConf.load()
  runApplication<AuthApplication>(*args)
}
