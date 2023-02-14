package com.example.mobileSales.service.servicesimpl;


import com.example.mobileSales.document.BrandDetails;
import com.example.mobileSales.dto.BrandDto;
import com.example.mobileSales.exception.BrandExistException;
import com.example.mobileSales.exception.MobileNotFoundException;
import com.example.mobileSales.repository.BrandRepository;
import com.example.mobileSales.repository.MobileRepository;
import com.example.mobileSales.service.BrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.*;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    MobileRepository mobileRepository;

    public Mono<BrandDetails> addBrand(BrandDto brandDto) {
        BrandDetails brand = new BrandDetails();
        BeanUtils.copyProperties(brandDto, brand);
        return brandRepository.existsById(brand.getBrandName())
                .flatMap(bool -> {
                    if (bool) {
                        return Mono.error(new BrandExistException());
                    }
                    else {
                        return brandRepository.save(brand);
                    }
                })
                .onErrorReturn(new BrandDetails());
    }

    public Mono<BrandDetails> updateBrandByName(BrandDto brandDto) {
        BrandDetails brand = new BrandDetails();
        BeanUtils.copyProperties(brandDto, brand);
        Mono<BrandDetails> mobile1 = brandRepository.findById(brand.getBrandName());
        return mobile1
                .switchIfEmpty(Mono.empty())
                .flatMap(f -> brandRepository.save(brand));
    }
    public Mono<BrandDetails> getBrandByName(String name) {
        return brandRepository.findById(name)
                .switchIfEmpty(Mono.empty());
    }
    public Mono<BrandDetails> deleteBrandByName(String name) {
        return brandRepository.findById(name)
                .switchIfEmpty(Mono.empty())
                .flatMap(t -> brandRepository.delete(t).then(Mono.just(t)));
    }
    public Flux<BrandDetails> getAllBrands() {
        return brandRepository
                .findAll()
                .switchIfEmpty(Flux.empty());
    }

    public Mono<BrandDetails> addMobileToBrand(String mobileName, String brandName) {
        return mobileRepository.existsById(mobileName).log()
                .flatMap(bool -> {
                    if (bool) {
                        return brandRepository.findById(brandName).log()
                                .flatMap(brand -> {
                                    List<String> mobiles = brand.getMobiles() == null ? new ArrayList<>() : brand.getMobiles();
                                    mobiles.add(mobileName);
                                    brand.setMobiles(mobiles);
                                    return brandRepository.save(brand);
                                });

                    }
                    else {
                        return Mono.error(new MobileNotFoundException());
                    }
                })
                .onErrorReturn(new BrandDetails());
    }

    public Mono<BrandDetails> deleteMobileFromBrand(String mobileName, String brandName) {
        return mobileRepository.existsById(mobileName).log()
                .flatMap(bool -> {
                    if (bool) {
                        return brandRepository.findById(brandName).log()
                                .flatMap(brand -> {
                                    List<String> mobiles = brand.getMobiles();
                                    if(mobiles.contains(mobileName)) {
                                        mobiles.remove(mobileName);
                                        return brandRepository.save(brand);
                                    }
                                    else {
                                        return Mono.error(new MobileNotFoundException());
                                    }
                                })
                                .onErrorReturn(new BrandDetails());

                    }
                    else {
                        return Mono.error(new MobileNotFoundException());
                    }
                })
                .onErrorReturn(new BrandDetails());
    }


}
