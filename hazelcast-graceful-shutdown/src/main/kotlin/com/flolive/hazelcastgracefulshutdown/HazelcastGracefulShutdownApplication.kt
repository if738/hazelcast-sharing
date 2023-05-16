package com.flolive.hazelcastgracefulshutdown

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.flolive.common", "com.flolive.hazelcastgracefulshutdown"])
class HazelcastGracefulShutdownApplication

fun main(args: Array<String>) {
	runApplication<HazelcastGracefulShutdownApplication>(*args)
}
