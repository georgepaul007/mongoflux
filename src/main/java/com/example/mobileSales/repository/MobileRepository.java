package com.example.mobileSales.repository;

import com.example.mobileSales.document.Mobile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface MobileRepository extends ReactiveMongoRepository<Mobile, String> {
    Mono<Mobile> findByName(String name);
    Mono<Boolean> existsByName(String name);
}
