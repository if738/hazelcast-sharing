package com.flolive.hazelcastputsetevict

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = ["com.flolive.common", "com.flolive.hazelcastEvict"])
@EnableScheduling
class HazelcastEvictApplication

fun main(args: Array<String>) {
	runApplication<HazelcastEvictApplication>(*args)
}
