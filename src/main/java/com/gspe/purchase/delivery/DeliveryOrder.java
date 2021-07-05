package com.gspe.purchase.delivery;

import com.gspe.purchase.Product;
import com.gspe.purchase.po.PurchaseOrder;
import com.gspe.purchase.vendor.Vendor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id")
    private PurchaseOrder purchaseOrder;

    @OneToMany(mappedBy = "deliveryOrder")
    private List<DeliveryOrderProduct> products;
}
