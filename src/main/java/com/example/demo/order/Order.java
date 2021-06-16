package com.example.demo.order;


import com.example.demo.coupon.Coupon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private double price;

    @OneToOne(mappedBy = "order")
    private Coupon coupon;
}
