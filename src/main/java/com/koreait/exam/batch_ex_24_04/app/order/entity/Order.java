package com.koreait.exam.batch_ex_24_04.app.order.entity;

import com.koreait.exam.batch_ex_24_04.app.base.entity.BaseEntity;
import com.koreait.exam.batch_ex_24_04.app.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "product_order")
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = ALL, orphanRemoval = true)
    private List<OrderItem> OrderItems = new ArrayList<>();


    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);

        OrderItems.add(orderItem);
    }
}