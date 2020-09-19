package de.florianmarsch.spring.fussballmanager.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import javax.persistence.EntityManager

@Configuration
class RestConfiguration : RepositoryRestConfigurer {
    @Autowired
    private val entityManager: EntityManager? = null

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
        entityManager?.metamodel?.entities?.toList()?.forEach {
            config.exposeIdsFor(it.javaType)
        }
    }

    init {
        println("apply RestConfiguration")
    }
}