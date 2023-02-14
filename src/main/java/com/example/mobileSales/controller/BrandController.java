package com.example.mobileSales.controller;

import com.example.mobileSales.document.BrandDetails;
import com.example.mobileSales.dto.BrandDto;
import com.example.mobileSales.dto.MobileDto;
import com.example.mobileSales.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/brand")
@RestController
public class BrandController {
    @Autowired
    BrandService brandService;

    @PostMapping("/addBrand")
    public ResponseEntity<Mono<String>> addOrInsertBrand(@RequestBody BrandDto brandDto) {
        return new ResponseEntity<>(brandService.addBrand(brandDto)
                .map(brand -> {
                    if (brand.getBrandName() == null) {
                        return "Brand already exists";
                    } else {
                        return "Brand added!";
                    }
                })
                , HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Mono<String>> updateBrandByName(@RequestBody BrandDto brandDto) {
        return new ResponseEntity<>(brandService.updateBrandByName(brandDto)
                .map(mobile1 -> "Brand Updated!")
                .defaultIfEmpty("Brand not found")
                , HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Flux<BrandDetails>> getAllMobiles() {
        return new ResponseEntity<>(brandService.getAllBrands(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Mono<BrandDetails>> getBrandByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(brandService.getBrandByName(name), HttpStatus.OK);
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<Mono<String>> deleteBrandByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(brandService.deleteBrandByName(name)
                .map(mobile -> "Brand Deleted!")
                .defaultIfEmpty("Brand Not Found!")
                , HttpStatus.OK);
    }
    @PostMapping("/addMobile/{mobileName}/{brandName}")
    public ResponseEntity<Mono<String>> addMobileToBrand(@PathVariable("mobileName") String mobileName, @PathVariable("brandName") String brandName) {

        return new ResponseEntity<>(brandService.addMobileToBrand(mobileName, brandName)
                .map(brand -> {
                    if(brand.getBrandName() == null){
                        return "Mobile does not exist";
                    }else{
                        return "Mobile added!";
                    }
                })
                , HttpStatus.OK);
    }

    @DeleteMapping("/removeMobile/{mobileName}/{brandName}")
    public ResponseEntity<Mono<String>> deleteMobileFromBrand(@PathVariable("mobileName") String mobileName,@PathVariable("brandName")  String brandName) {
        return new ResponseEntity<>(brandService.deleteMobileFromBrand(mobileName, brandName)
                .map(brand -> {
                    if(brand.getBrandName() == null){
                        return "Mobile does not exist";
                    }else{
                        return "Mobile deleted!";
                    }
                })
                , HttpStatus.OK);

    }
}
