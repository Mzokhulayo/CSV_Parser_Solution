package com.eviro.assesment.grad001.mzokhulayomdubeki.ParserAPP;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.eviro.assesment.grad001.mzokhulayomdubeki")
public class JpaConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
