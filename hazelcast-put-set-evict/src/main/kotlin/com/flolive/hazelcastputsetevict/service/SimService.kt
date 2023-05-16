package com.flolive.hazelcastputsetevict.service

import com.flolive.common.generator.DataGenerator
import com.flolive.common.repository.SimHazelcastRepository
import org.apache.logging.log4j.kotlin.logger
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SimService(private val simHazelcastRepository: SimHazelcastRepository) {

    private val log = logger()

    @Scheduled(fixedDelay = 1000)
    fun enrichCache() {
        DataGenerator.generateSims(271).let {
            //Todo use .saveAll() for arrays
            simHazelcastRepository.saveAll(it)
        }
    }


}