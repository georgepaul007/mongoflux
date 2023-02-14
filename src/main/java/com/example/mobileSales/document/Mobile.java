package com.example.mobileSales.document;


import com.sun.istack.internal.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Mobile {
    @Id
    @NotNull
    String name;

    String ram;

    String color;
}
