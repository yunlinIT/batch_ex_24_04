package com.koreait.exam.batch_ex_24_04.app.cart.service;

import com.koreait.exam.batch_ex_24_04.app.cart.entity.CartItem;
import com.koreait.exam.batch_ex_24_04.app.cart.repository.CartItemRepository;
import com.koreait.exam.batch_ex_24_04.app.member.entity.Member;
import com.koreait.exam.batch_ex_24_04.app.product.entity.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;

    public void addItem(Member member, ProductOption productOption, int quantity) {
        CartItem cartItem = CartItem.builder()
                .member(member)
                .productOption(productOption)
                .quantity(quantity)
                .build();

        cartItemRepository.save(cartItem);
    }
}