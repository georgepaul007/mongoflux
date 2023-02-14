package com.example.mobileSales.service;

import com.example.mobileSales.document.Mobile;
import com.example.mobileSales.dto.BrandDto;
import com.example.mobileSales.dto.MobileDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MobileService {
    Mono<Mobile> getMobileByName(String name);
    Mono<Mobile> updateMobileByName(MobileDto mobileDto);
    Mono<Mobile> deleteMobileByName(String name);
    Flux<Mobile> getAllMobiles();
    Mono<Mobile> addMobile(MobileDto mobileDto);
}
