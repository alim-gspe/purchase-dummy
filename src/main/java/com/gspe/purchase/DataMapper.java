package com.gspe.purchase;

import com.gspe.purchase.delivery.DeliveryOrder;
import com.gspe.purchase.delivery.DeliveryOrderProduct;
import com.gspe.purchase.dto.DeliveryOrderDto;
import com.gspe.purchase.dto.ProductDto;
import com.gspe.purchase.dto.PurchaseOrderDto;
import com.gspe.purchase.dto.VendorDto;
import com.gspe.purchase.po.PurchaseOrder;
import com.gspe.purchase.po.PurchaseOrderProduct;
import com.gspe.purchase.vendor.Vendor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataMapper {

    public List<VendorDto> vendorMap(List<Vendor> vendors) {
        List<VendorDto> vendorDtos = new ArrayList<>();
        for (Vendor vendor : vendors) {
            VendorDto vendorDto = new VendorDto();
            vendorDto.setId(vendor.getId());
            vendorDto.setName(vendor.getName());
            vendorDto.setVendorCode(vendor.getVendorCode());
            vendorDtos.add(vendorDto);
        }
        return vendorDtos;
    }
    public List<PurchaseOrderDto> purchaseOrderMap(List<PurchaseOrder> purchaseOrders) {
        List<PurchaseOrderDto> purchaseOrderDtos = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
            purchaseOrderDto.setId(purchaseOrder.getId());
            purchaseOrderDto.setName(purchaseOrder.getName());
            purchaseOrderDto.setAddress(purchaseOrder.getAddress());
            purchaseOrderDto.setFob(purchaseOrder.getFob());
            purchaseOrderDto.setBillTo(purchaseOrder.getBillTo());
            purchaseOrderDto.setCurrency(purchaseOrder.getCurrency());
            purchaseOrderDto.setTotal(totalPo(purchaseOrder.getProducts()));
            purchaseOrderDto.setProducts(productPurchaseOrderMap(purchaseOrder.getProducts()));
            purchaseOrderDtos.add(purchaseOrderDto);
        }
        return purchaseOrderDtos;
    }

    public BigDecimal totalPo(List<PurchaseOrderProduct> products) {
        BigDecimal total = BigDecimal.ZERO;
        for (PurchaseOrderProduct purchaseOrderProduct : products) {
            BigDecimal totalProduct = purchaseOrderProduct.getProduct().getUnitPrice().multiply(BigDecimal.valueOf(purchaseOrderProduct.getQuantity()));
            BigDecimal discount = totalProduct.multiply(BigDecimal.valueOf(purchaseOrderProduct.getDiscount() / 100));
            total = total.add(totalProduct.subtract(discount));
        }
        return total;
    }

    public List<DeliveryOrderDto> deliveryOrderMap(List<DeliveryOrder> deliveryOrders) {
        List<DeliveryOrderDto> deliveryOrderDtos = new ArrayList<>();
        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            DeliveryOrderDto deliveryOrderDto = new DeliveryOrderDto();
            deliveryOrderDto.setId(deliveryOrder.getId());
            deliveryOrderDto.setName(deliveryOrder.getName());
            deliveryOrderDto.setPurchaseOrder(purchaseOrder(deliveryOrder.getPurchaseOrder()));
            deliveryOrderDto.setProducts(productDeliveryOrderMap(deliveryOrder.getProducts()));
            deliveryOrderDtos.add(deliveryOrderDto);
        }
        return deliveryOrderDtos;
    }

    public PurchaseOrderDto purchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
        purchaseOrderDto.setId(purchaseOrder.getId());
        purchaseOrderDto.setName(purchaseOrder.getName());
        purchaseOrderDto.setAddress(purchaseOrder.getAddress());
        purchaseOrderDto.setFob(purchaseOrder.getFob());
        purchaseOrderDto.setBillTo(purchaseOrder.getBillTo());
        purchaseOrderDto.setCurrency(purchaseOrder.getCurrency());
        purchaseOrderDto.setTotal(totalPo(purchaseOrder.getProducts()));
        return purchaseOrderDto;
    }

    public List<ProductDto> productPurchaseOrderMap(List<PurchaseOrderProduct> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (PurchaseOrderProduct purchaseOrderProduct : products) {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(purchaseOrderProduct.getProduct().getId());
            productDto.setProductDetail(purchaseOrderProduct.getProduct().getName());
            productDto.setUnitMeasure(purchaseOrderProduct.getProduct().getUnitMeasure());
            productDto.setQty(purchaseOrderProduct.getQuantity());
            productDto.setUnitPrice(purchaseOrderProduct.getProduct().getUnitPrice());
            productDto.setDiscount(purchaseOrderProduct.getDiscount());
            val total = purchaseOrderProduct.getProduct().getUnitPrice().multiply(BigDecimal.valueOf(purchaseOrderProduct.getQuantity()));
            val discount = total.multiply(BigDecimal.valueOf(purchaseOrderProduct.getDiscount() / 100.0));
            productDto.setTotal(total.subtract(discount));
            productDto.setPoNumber(purchaseOrderProduct.getPurchaseOrder().getName());
            productDto.setDoNumber(null);
            productDto.setProject(purchaseOrderProduct.getProject());
            productDto.setDepartment(purchaseOrderProduct.getDepartment());
            productDto.setReference(purchaseOrderProduct.getReference());
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public List<ProductDto> productDeliveryOrderMap(List<DeliveryOrderProduct> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (DeliveryOrderProduct deliveryOrderProduct : products) {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(deliveryOrderProduct.getProduct().getId());
            productDto.setProductDetail(deliveryOrderProduct.getProduct().getName());
            productDto.setUnitMeasure(deliveryOrderProduct.getProduct().getUnitMeasure());
            productDto.setQty(deliveryOrderProduct.getQuantity());
            productDto.setUnitPrice(deliveryOrderProduct.getProduct().getUnitPrice());
            productDto.setDiscount(deliveryOrderProduct.getDiscount());
            val total = deliveryOrderProduct.getProduct().getUnitPrice().multiply(BigDecimal.valueOf(deliveryOrderProduct.getQuantity()));
            val discount = total.multiply(BigDecimal.valueOf(deliveryOrderProduct.getDiscount() / 100.0));
            productDto.setTotal(total.subtract(discount));
            productDto.setPoNumber(deliveryOrderProduct.getDeliveryOrder().getPurchaseOrder().getName());
            productDto.setDoNumber(deliveryOrderProduct.getDeliveryOrder().getName());
            productDto.setProject(deliveryOrderProduct.getProject());
            productDto.setDepartment(deliveryOrderProduct.getDepartment());
            productDto.setReference(deliveryOrderProduct.getReference());
            productDtos.add(productDto);
        }
        return productDtos;
    }

}
