package com.example.mobileSales.repository;

import com.example.mobileSales.document.BrandDetails;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends ReactiveMongoRepository<BrandDetails, String> {
}
