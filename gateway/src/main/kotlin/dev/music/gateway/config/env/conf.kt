package dev.music.gateway.config.env

import io.github.cdimascio.dotenv.Dotenv

class EnvConf {

  companion object {
    fun load() {
      val entries = Dotenv.configure()
        .directory("./gateway")
        .filename(".env")
        .load()
        .entries()

      for (entry in entries) {
        System.setProperty(entry.key, entry.value)
      }
    }
  }
}