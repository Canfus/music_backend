import com.google.protobuf.gradle.*

val protobufVersion = "3.25.3"
val grpcVersion     = "1.62.2"
val grpcKtVersion   = "1.4.0"

plugins {
	id("java-library")
	id("com.google.protobuf") version "0.9.4"
	kotlin("jvm") version "2.2.0"
}

group = "dev.music"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
	withSourcesJar()
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")
	implementation("io.grpc:grpc-kotlin-stub:$grpcKtVersion")
	implementation("io.grpc:grpc-protobuf:$grpcVersion")
	implementation("io.grpc:grpc-stub:$grpcVersion")
	implementation(kotlin("stdlib-jdk8"))
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:$protobufVersion"
	}
	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
		}
		id("grpckt") {
			artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKtVersion:jdk8@jar"
		}
	}
	generateProtoTasks {
		all().forEach {
			it.plugins {
				id("grpc")
				id("grpckt")
			}
		}
	}
}
