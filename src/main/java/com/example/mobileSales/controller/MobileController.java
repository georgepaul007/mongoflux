package com.example.mobileSales.controller;


import com.example.mobileSales.document.Mobile;
import com.example.mobileSales.dto.MobileDto;
import com.example.mobileSales.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/mobile")
@RestController
public class MobileController {

    @Autowired
    MobileService mobileService;

    @GetMapping("/{name}")
    public ResponseEntity<Mono<Mobile>> getMobileByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(mobileService.getMobileByName(name), HttpStatus.OK);
//                .map(mobile -> ResponseEntity.status(HttpStatus.OK).body(mobile))
//                .defaultIfEmpty(ResponseEntity.status(HttpStatus.OK).body(null));
    }

    @PutMapping
    public ResponseEntity<Mono<String>> updateMobileByName(@RequestBody MobileDto mobileDto) {
//        Mono<Mobile> mobileMono = mobileServiceImpl.updateMobileById(mobile);
        return new ResponseEntity<>(mobileService.updateMobileByName(mobileDto)
                .map(mobile1 -> "Mobile Updated!")
                .defaultIfEmpty("Mobile not found")
                , HttpStatus.OK);
//                .map(mobile1 -> ResponseEntity.status(HttpStatus.OK).body("Mobile Updated!"))
//                .defaultIfEmpty(ResponseEntity.status(HttpStatus.OK).body("Mobile Not Found!"));


//        return "Success" ;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Flux<Mobile>> getAllMobiles() {
        return new ResponseEntity<>(mobileService.getAllMobiles(), HttpStatus.OK);
//                .map(mobile -> ResponseEntity.status(HttpStatus.OK).body(mobile))
//                .defaultIfEmpty(ResponseEntity.status(HttpStatus.OK).body(null));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Mono<String>> deleteMobileByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(mobileService.deleteMobileByName(name)
                .map(mobile -> "Mobile Deleted!")
                .defaultIfEmpty("Mobile Not Found!")
                , HttpStatus.OK);
//                .map(mobile -> ResponseEntity.status(HttpStatus.OK).body("Mobile Deleted!"))
//                .defaultIfEmpty(ResponseEntity.status(HttpStatus.OK).body("Mobile Not Found!"));
    }

    @PostMapping("/add")
    public ResponseEntity<Mono<String>> addMobile(@RequestBody MobileDto mobileDto) {
        Mono<Mobile> mobileMono = mobileService.addMobile(mobileDto);
        return new ResponseEntity<>(mobileMono
                .map(mobile -> {
                    if(mobile.getName() == null){
                        return "Mobile already exists";
                    }else{
                        return "mobile added!";
                    }
                })
                , HttpStatus.OK);
//                .map(mobile -> ResponseEntity.status(HttpStatus.OK).body(mobile))
//                .cast(ResponseEntity.class)
//                .defaultIfEmpty(ResponseEntity.status(HttpStatus.OK).body(null));
    }


}
