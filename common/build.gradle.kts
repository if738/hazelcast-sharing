import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

group = "com.flolive"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	api("com.hazelcast:hazelcast-spring:5.2.3")
	api("com.hazelcast:hazelcast:5.2.3")
	api("org.springframework.boot:spring-boot-starter")
	api("org.jetbrains.kotlin:kotlin-reflect")
	api("org.springframework.boot:spring-boot-starter-test")
	api("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
