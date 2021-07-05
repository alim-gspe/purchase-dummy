package com.gspe.purchase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DeliveryOrderDto {
    private Long id;
    private String name;
    private PurchaseOrderDto purchaseOrder;
    private List<ProductDto> products;
}
