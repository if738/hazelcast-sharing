package com.flolive.common.generator

import com.flolive.common.entity.Sim
import java.util.*
import kotlin.random.Random

class DataGenerator {

    companion object {
        fun generateSims(count: Int): List<Sim> {
            return List(count) {
                Sim(id = randomUUID().toString(), iccid = randomIccid())
            }
        }

        private fun randomIccid(): String {
            return List(19) {
                Random.nextInt(9).toString()
            }.reduce { v1, v2 -> v1 + v2 }
        }

        private fun randomUUID(): UUID {
            return UUID.randomUUID()
        }
    }

}
