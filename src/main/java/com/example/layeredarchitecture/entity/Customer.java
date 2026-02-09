package com.example.layeredarchitecture.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class Customer {
    private String id;
    private String name;
    private String address;
}
