package com.example.mobileSales.service;

import com.example.mobileSales.document.BrandDetails;
import com.example.mobileSales.dto.BrandDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BrandService {
    Mono<BrandDetails> addBrand(BrandDto brandDto);
    Mono<BrandDetails> updateBrandByName(BrandDto brandDto);
    Mono<BrandDetails> getBrandByName(String name);
    Mono<BrandDetails> deleteBrandByName(String name);
    Flux<BrandDetails> getAllBrands();
    Mono<BrandDetails> addMobileToBrand(String mobileName, String brandName);
    Mono<BrandDetails> deleteMobileFromBrand(String mobileName, String brandName);
}
