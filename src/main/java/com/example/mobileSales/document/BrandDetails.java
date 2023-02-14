package com.example.mobileSales.document;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.*;
@Document
@Data
public class BrandDetails {
    @Id
    String brandName;
    String brandReview;
    List<String> mobiles;
}
