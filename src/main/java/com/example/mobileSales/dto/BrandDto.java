package com.example.mobileSales.dto;

import com.example.mobileSales.document.Mobile;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.*;
@Data
public class BrandDto {
    String brandName;
    String brandReview;
}
