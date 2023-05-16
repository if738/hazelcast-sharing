package com.flolive.common.repository

import com.flolive.common.entity.Sim
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.query.Predicate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class SimHazelcastRepository(private val hazelcastInstance: HazelcastInstance) {

    fun save(sim: Sim) {
        hazelcastInstance.getMap<UUID, Sim>(Sim::class.simpleName!!).set(UUID.randomUUID(), sim)
    }

    fun saveAll(sims: List<Sim>) {
        val simsMap = sims.map { UUID.randomUUID() to it }.toMap()
        hazelcastInstance.getMap<UUID, Sim>(Sim::class.simpleName!!).putAll(simsMap)
    }

    fun saveAllAsync(sims: List<Sim>) {
        val simsMap = sims.map { UUID.randomUUID() to it }.toMap()
        hazelcastInstance.getMap<UUID, Sim>(Sim::class.simpleName!!).putAllAsync(simsMap)
    }

    //Don't do this, just for example
    fun findAll(): List<Sim> {
        return hazelcastInstance.getMap<UUID, Sim>(Sim::class.simpleName!!)
            .toMutableList()
            .map { it.value }
    }

    //Avoid this
    fun filter(predicate: Predicate<UUID, Sim>): List<Sim> {
        return hazelcastInstance.getMap<UUID, Sim>(Sim::class.simpleName!!)
            .values(predicate)
            .toMutableList()
    }

}
