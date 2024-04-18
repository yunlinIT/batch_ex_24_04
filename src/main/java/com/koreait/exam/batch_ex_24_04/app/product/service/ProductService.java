package com.koreait.exam.batch_ex_24_04.app.product.service;

import com.koreait.exam.batch_ex_24_04.app.member.entity.Member;
import com.koreait.exam.batch_ex_24_04.app.member.repository.MemberRepository;
import com.koreait.exam.batch_ex_24_04.app.product.entity.Product;
import com.koreait.exam.batch_ex_24_04.app.product.entity.ProductOption;
import com.koreait.exam.batch_ex_24_04.app.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(String name, int price, String makerShopName, List<ProductOption> options) {
        Product product = Product.builder()
                .name(name)
                .price(price)
                .makerShopName(makerShopName).build();

        for(ProductOption option : options) {
            product.addOption(option);
        }

        productRepository.save(product);

        return product;
    }
}