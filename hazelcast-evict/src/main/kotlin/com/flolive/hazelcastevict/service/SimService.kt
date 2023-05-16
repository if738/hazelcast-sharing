package com.flolive.hazelcastevict.service

import com.flolive.common.generator.DataGenerator
import com.flolive.common.repository.SimHazelcastRepository
import org.apache.logging.log4j.kotlin.logger
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SimService(private val simHazelcastRepository: SimHazelcastRepository) {

    private val log = logger()

    @Scheduled(fixedDelay = 1)
    fun enrichCache() {
        DataGenerator.generateSims(271).let {
            //Todo use .saveAll() for arrays
            simHazelcastRepository.saveAll(it)
        }
//        log.info("Data already saved!")
    }


    @Scheduled(fixedDelay = 10_000)
    fun getAll() {
        //TODO never do findAll, it's for presentation
        val findAll = simHazelcastRepository.findAll()
        log.info("Data found, size=${findAll.size}")
    }

}