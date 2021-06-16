package com.example.demo.coupon;

import com.example.demo.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue
    @Column(name = "coupon_id")
    private Long id;

    @Column(name = "is_use")
    private boolean isUse = false;

    @OneToOne
    private Order order;
}
