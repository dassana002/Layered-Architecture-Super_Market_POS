package com.example.layeredarchitecture.entity;

import lombok.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class OrderDetail {
    private String itemCode;
    private int qty;
    private BigDecimal unitPrice;
}
