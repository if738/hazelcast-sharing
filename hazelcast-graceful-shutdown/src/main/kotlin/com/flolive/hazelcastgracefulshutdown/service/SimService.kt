package com.flolive.hazelcastgracefulshutdown.service

import com.flolive.common.generator.DataGenerator
import com.flolive.common.repository.SimHazelcastRepository
import jakarta.annotation.PostConstruct
import org.apache.logging.log4j.kotlin.logger
import org.springframework.stereotype.Component

@Component
class SimService(private val simHazelcastRepository: SimHazelcastRepository) {

    private val log = logger()

    @PostConstruct
    fun enrichCache() {
        val isGenerate = System.getenv().entries.find { it.key == "GENERATE_DATA" }!!.value.toBoolean()
        if (isGenerate){
            DataGenerator.generateSims(100_000).forEach {
                simHazelcastRepository.save(it)
            }
            log.info("Data already saved!")
        }
    }

}