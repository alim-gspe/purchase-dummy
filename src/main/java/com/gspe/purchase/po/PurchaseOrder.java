package com.gspe.purchase.po;

import com.gspe.purchase.vendor.Vendor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String fob;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String billTo;
    private String currency;

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchaseOrderProduct> products;
}
