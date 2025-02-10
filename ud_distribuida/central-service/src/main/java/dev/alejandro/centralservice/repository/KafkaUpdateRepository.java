package dev.alejandro.centralservice.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class KafkaUpdateRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void enableKafkaDelete(){
        entityManager.createNativeQuery("DO $$ BEGIN PERFORM set_kafka_update(); END $$ language plpgsql;").executeUpdate();
    }

    @Transactional
    public void disableKafkaDelete(){
        entityManager.createNativeQuery("DO $$ BEGIN PERFORM unset_kafka_update(); END $$ language plpgsql;").executeUpdate();
    }
}
