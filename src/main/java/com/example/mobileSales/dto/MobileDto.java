package com.example.mobileSales.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class MobileDto {
    String name;
    String ram;
    String color;
}
