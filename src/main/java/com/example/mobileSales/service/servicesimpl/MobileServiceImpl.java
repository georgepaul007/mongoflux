package com.example.mobileSales.service.servicesimpl;


import com.example.mobileSales.document.BrandDetails;
import com.example.mobileSales.document.Mobile;
import com.example.mobileSales.dto.BrandDto;
import com.example.mobileSales.dto.MobileDto;
import com.example.mobileSales.exception.MobileExistException;
import com.example.mobileSales.repository.BrandRepository;
import com.example.mobileSales.repository.MobileRepository;
import com.example.mobileSales.service.MobileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class MobileServiceImpl implements MobileService {
    @Autowired
    MobileRepository mobileRepository;

    @Autowired
    BrandRepository brandRepository;
    public Mono<Mobile> getMobileByName(String name) {
        return mobileRepository.findById(name)
                .switchIfEmpty(Mono.empty());
//        mobileRepository.findById(id)
//                .map(mobile -> {
//                    mobile.setId(id);
//                    return mobile;
//                });
//        mobileRepository.findById(id)
//                .flatMap(mobile -> {
//                    mobile.setId(id);
//                    return Mono.just(mobile);
//                });
//        return mobileRepository.findById(id).flatMap(s -> {
//            s.setColor("red");
//
//        });
    }
    public Mono<Mobile> updateMobileByName(MobileDto mobileDto) {
        Mobile mobile = new Mobile();
        BeanUtils.copyProperties(mobileDto, mobile);
        Mono<Mobile> mobile1 = mobileRepository.findById(mobile.getName());
        return mobile1
                .switchIfEmpty(Mono.empty())
                .flatMap(f -> mobileRepository.save(mobile));
//                .then(mobileRepository.save(mobile));

//        Mono<Mobile> mobile1 = mobileRepository.findById(id);
//        mobile1.subscribe(s -> {
//            System.out.println(s.toString());
//            BeanUtils.copyProperties(mobile, s);
//            System.out.println("s = " + s);
//            mobileRepository.deleteById(id);
//            mobileRepository.save(mobile);
//        });
//
//        return mobileRepository.deleteById(id);
//
//        Function<Mono<Mobile>, Mono<Mobile>> updateMobile = data -> {
//            BeanUtils.copyProperties(mobile, data);
//            System.out.println("s = " + data);
//            mobileRepository.deleteById(id);
//            return mobileRepository.save(data);
//        };
//        return mobileRepository.findById(id)
//                .transform(updateMobile);
                }
    public Mono<Mobile> deleteMobileByName(String name) {
        return mobileRepository.findById(name)
                .switchIfEmpty(Mono.empty())
                .flatMap(t -> mobileRepository.delete(t).then(Mono.just(t)));
    }
    public Flux<Mobile> getAllMobiles() {
        return mobileRepository
                .findAll()
                .switchIfEmpty(Flux.empty());
//        return Flux.empty();
    }
    public Mono<Mobile> addMobile(MobileDto mobileDto) {
        Mobile mobile = new Mobile();
        BeanUtils.copyProperties(mobileDto, mobile);
//        mobileRepository.findById(mobile.getId())
//                .hasElement().map(bool -> {
//            if (bool) {
//                Mono.error(new MobileExistsException());
//            }
//        })
//        Mono<Mobile> ifExists = mobileRepository.findById(mobile.getId());
        return mobileRepository.existsById(mobile.getName())
                .flatMap(bool -> {
                    if (bool) {
                        return Mono.error(new MobileExistException());
                    }
                    else {
                        return mobileRepository.save(mobile);
                    }
                })
                .onErrorReturn(new Mobile());

    }
    public Mono addBrand(BrandDto brandDto) {
        /**
         * Flux.fromIterable(request.getIds()).flatMap(id -> repo.findById(id)).flatMap(obj -> someOperation());
         */
        BrandDetails brandDetails = new BrandDetails();
        BeanUtils.copyProperties(brandDto, brandDetails);
        return brandRepository.save(brandDetails);
    }
//    public Flux<Mobile> addAllMobile() {
//        return mobileRepository.findAll()
//                .flatMap(id -> mobileRepository.findById(id.getId())).log().delayElements(Duration.ofMillis(1000))
//                .flatMap(mobile -> {
//                    if(Integer.parseInt(mobile.getId()) == 1) {
//                        mobile.setId("100");
//                    }
//                    return Mono.just(mobile);
//                }).log();
//    }

}
