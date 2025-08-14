package dev.music.gateway

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import dev.music.gateway.config.env.EnvConf

@SpringBootApplication
class GatewayApplication

fun main(args: Array<String>) {
  EnvConf.load()
  runApplication<GatewayApplication>(*args)
}
