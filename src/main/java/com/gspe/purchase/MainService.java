package com.gspe.purchase;

import com.gspe.purchase.delivery.DeliveryOrderRepository;
import com.gspe.purchase.dto.DeliveryOrderDto;
import com.gspe.purchase.dto.PurchaseOrderDto;
import com.gspe.purchase.dto.VendorDto;
import com.gspe.purchase.po.PurchaseOrderRepository;
import com.gspe.purchase.vendor.VendorRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MainService {
    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    DataMapper dataMapper;

    public List<VendorDto> getVendors() {
        val result = dataMapper.vendorMap(vendorRepository.findAll());
        return result;
    }

    public List<PurchaseOrderDto> getPurchaseOrder(Long vendorId) {
        val result = dataMapper.purchaseOrderMap(purchaseOrderRepository.findByVendor_Id(vendorId));
        return result;
    }

    public List<DeliveryOrderDto> getDeliveryOrder(Long vendorId) {
        val result = dataMapper.deliveryOrderMap(deliveryOrderRepository.findByVendor_Id(vendorId));
        return result;
    }
}
