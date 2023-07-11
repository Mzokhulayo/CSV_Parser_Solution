package com.eviro.assesment.grad001.mzokhulayomdubeki.ParserAPP;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Java Persistence API (JPA) related configurations.
 */
@Configuration
public class JpaConfig {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates and provides the EntityManager bean.
     *
     * @return The EntityManager instance.
     */
    @Bean
    public EntityManager getEntityManager() {
        return entityManager;
    }
}