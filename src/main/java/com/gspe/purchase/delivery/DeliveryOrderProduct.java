package com.gspe.purchase.delivery;

import com.gspe.purchase.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class DeliveryOrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private int discount;
    private String project;
    private String department;
    private String reference;

    @ManyToOne
    @JoinColumn(name = "delivery_order_id", referencedColumnName = "id")
    private DeliveryOrder deliveryOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
