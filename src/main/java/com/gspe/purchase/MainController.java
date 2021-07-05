package com.gspe.purchase;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping("/api/vendors")
    ResponseEntity<?> getVendors() {
        val result = mainService.getVendors();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/purchase-orders/{vendorId}")
    ResponseEntity<?> getPurchaseOrder(@PathVariable Long vendorId) {
        val result = mainService.getPurchaseOrder(vendorId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/delivery-orders/{vendorId}")
    ResponseEntity<?> getDeliveryOrder(@PathVariable Long vendorId) {
        val result = mainService.getDeliveryOrder(vendorId);
        return ResponseEntity.ok(result);
    }
}
