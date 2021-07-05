package com.gspe.purchase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productDetail;
    private String unitMeasure;
    private int qty;
    private BigDecimal unitPrice;
    private int discount;
    private BigDecimal total;
    private String project;
    private String department;
    private String reference;
    private String poNumber;
    private String doNumber;
}
