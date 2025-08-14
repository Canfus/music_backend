package dev.music.gateway.config.web_mvc

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMVCConf : WebMvcConfigurer {

  override fun addCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/**")
      .allowedOrigins("http://localhost")
      .allowCredentials(true)
  }
}