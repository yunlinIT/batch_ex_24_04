package com.koreait.exam.batch_ex_24_04.app.base;

import com.koreait.exam.batch_ex_24_04.app.cart.service.CartService;
import com.koreait.exam.batch_ex_24_04.app.member.entity.Member;
import com.koreait.exam.batch_ex_24_04.app.member.service.MemberService;
import com.koreait.exam.batch_ex_24_04.app.order.service.OrderService;
import com.koreait.exam.batch_ex_24_04.app.product.entity.Product;
import com.koreait.exam.batch_ex_24_04.app.product.entity.ProductOption;
import com.koreait.exam.batch_ex_24_04.app.product.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("dev")
public class DevInitData {
    @Bean
    public CommandLineRunner initData(MemberService memberService, ProductService productService, CartService cartService, OrderService orderService) {
        return args -> {
            String password = "{noop}1234";
            Member member1 = memberService.join("user1", password, "user1@test.com");
            Member member2 = memberService.join("user2", password, "user2@test.com");
            Member member3 = memberService.join("user3", password, "user3@test.com");
            Member member4 = memberService.join("user4", password, "user4@test.com");

            Product product1 = productService.create("셔츠 1", 68000, "평화-1-14",
                    Arrays.asList(new ProductOption("RED", "95"),
                            new ProductOption("RED", "100"),
                            new ProductOption("BLUE", "95"),
                            new ProductOption("BLUE", "100")));

            Product product2 = productService.create("반팔 1", 72000, "평화-1-14",
                    Arrays.asList(new ProductOption("BLACK", "95"),
                            new ProductOption("BLACK", "100"),
                            new ProductOption("WHITE", "95"),
                            new ProductOption("WHITE", "100")));

            ProductOption productOption__RED_95 = product1.getProductOptions().get(0);
            ProductOption productOption__BLUE_95 = product1.getProductOptions().get(2);

            cartService.addItem(member1, productOption__RED_95, 1); // productOption__RED_95 총 수량 1
            cartService.addItem(member1, productOption__RED_95, 2); // productOption__RED_95 총 수량 3
            cartService.addItem(member1, productOption__BLUE_95, 1); // productOption__BLUE_95 총 수량 1

            orderService.createFromCart(member1);
        };
    }
}