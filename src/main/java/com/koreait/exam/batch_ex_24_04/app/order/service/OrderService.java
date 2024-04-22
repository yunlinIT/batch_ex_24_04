package com.koreait.exam.batch_ex_24_04.app.order.service;

import com.koreait.exam.batch_ex_24_04.app.cart.entity.CartItem;
import com.koreait.exam.batch_ex_24_04.app.cart.service.CartService;
import com.koreait.exam.batch_ex_24_04.app.member.entity.Member;
import com.koreait.exam.batch_ex_24_04.app.member.service.MemberService;
import com.koreait.exam.batch_ex_24_04.app.order.entity.Order;
import com.koreait.exam.batch_ex_24_04.app.order.entity.OrderItem;
import com.koreait.exam.batch_ex_24_04.app.order.repository.OrderRepository;
import com.koreait.exam.batch_ex_24_04.app.product.entity.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final MemberService memberService;
    private final CartService cartService;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createFromCart(Member member) {
        // 입력된 회원의 장바구니 아이템들을 전부 가져온다.

        // 만약에 특정 장바구니의 상품 옵션이 판매불가 상태다. => 삭제
        // 만약에 특정 장바구니의 상품 옵션이 판매가능 상태다 => 주문 품목으로 옮긴 후 삭제
        List<CartItem> cartItems = cartService.getItemsByMember(member);

        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem : cartItems) {
            ProductOption productOption = cartItem.getProductOption();

            if(productOption.isOrderable(cartItem.getQuantity())){
                orderItems.add(new OrderItem(productOption,cartItem.getQuantity()));
            }

            cartService.deleteItem(cartItem);
        }

        return create(member,orderItems);

    }

    @Transactional
    public Order create(Member member, List<OrderItem> orderItems) {
        Order order = Order
                .builder()
                .member(member)
                .build();

        for(OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        orderRepository.save(order);

        return order;
    }

    @Transactional
    public void payByRestCashOnly(Order order) {
        Member orderer = order.getMember();

        long restCash = orderer.getRestCash();

        int payPrice = order.calculatePayPrice();

        if(payPrice > restCash){
            throw new RuntimeException("예치금이 부족해");
        }

        memberService.addCash(orderer,payPrice * -1, "주문결제__예치금결제");

        order.setPaymentDone();
        orderRepository.save(order);
    }

    @Transactional
    public void refund(Order order) {
        int payPrice = order.getPayPrice();
        memberService.addCash(order.getMember(),payPrice,"주문환불__예치금환불");


        order.setRefundDone();
        orderRepository.save(order);
    }
}