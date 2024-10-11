package com.example.demo.respository;

import com.example.demo.model.DnaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends MongoRepository<DnaEntity, Long> {

    long countByIsMutanteTrue();

    long countByIsMutanteFalse();
}
