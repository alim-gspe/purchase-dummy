package com.gspe.purchase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VendorDto {
    private Long id;
    private String name;
    private String vendorCode;
}
