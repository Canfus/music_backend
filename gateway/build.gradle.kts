val grpcKtVersion   = "1.4.0"

plugins {
  kotlin("jvm") version "2.2.0"
  kotlin("plugin.spring") version "1.9.25"
  id("org.springframework.boot") version "3.5.4"
  id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.music"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-web")

  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("io.github.cdimascio:dotenv-java:3.2.0")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

  implementation("net.devh:grpc-server-spring-boot-starter:3.1.0.RELEASE")
  implementation("io.grpc:grpc-kotlin-stub:$grpcKtVersion")
  implementation("dev.music:proto-contracts")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation("org.springframework.security:spring-security-test")

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
