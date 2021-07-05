package com.gspe.purchase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class PurchaseOrderDto {
    private Long id;
    private String name;
    private String fob;
    private String billTo;
    private String address;
    private String currency;
    private BigDecimal total;
    private List<ProductDto> products;
}
