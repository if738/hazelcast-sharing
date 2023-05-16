package com.flolive.common.configuration

import com.flolive.common.entity.Sim
import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.spring.context.SpringManagedContext
import jakarta.annotation.PreDestroy
import org.apache.logging.log4j.kotlin.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class HazelcastConfiguration(@Autowired val context: ApplicationContext) {

    @Bean(name = ["hazelcastInstance"])
    fun hazelcastInstance(): HazelcastInstance {
        val hazelcastInstance = Hazelcast.getHazelcastInstanceByName("dev")
        if (null != hazelcastInstance && hazelcastInstance.lifecycleService.isRunning) return hazelcastInstance
        return Hazelcast.newHazelcastInstance(config())
    }


    fun config(): Config {
        val config = Config()
        config.instanceName = "dev"
        config.serializationConfig
            .compactSerializationConfig
            .addClass(Sim::class.java)

        config.networkConfig.isPortAutoIncrement = true

        config.managedContext = SpringManagedContext(context)

        System.setProperty("hazelcast.discovery.enabled", "true")

        System.setProperty("hazelcast.shutdownhook.enabled", "false")
        System.setProperty("hazelcast.shutdownhook.policy", "GRACEFUL")

        System.setProperty("hazelcast.partition.migration.interval", "1")

//        System.setProperty("hazelcast.partition.backup.sync.interval", "1000000")
        System.setProperty("hazelcast.partition.max.parallel.migrations", "100")
//        System.setProperty("hazelcast.partition.max.parallel.replications", "1")

        config.jetConfig.isEnabled = true

        return config
    }

}

@Configuration
class HazelcastPreDestroyConfiguration(
    val hazelcastInstance: HazelcastInstance
) {

    private val log = logger()

    @PreDestroy
    fun destroy() {
        log.info { "Closing Cache Manager" }
        hazelcastInstance.shutdown()
    }

}
