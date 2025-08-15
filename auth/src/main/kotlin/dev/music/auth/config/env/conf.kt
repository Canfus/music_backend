package dev.music.auth.config.env

import io.github.cdimascio.dotenv.Dotenv

class EnvConf {

  companion object {
    fun load() {
      val env = Dotenv.configure()
        .directory("./auth")
        .filename(".env.local")
        .load()
        .entries()

      for (entry in env) {
        System.setProperty(entry.key, entry.value)
      }
    }
  }
}