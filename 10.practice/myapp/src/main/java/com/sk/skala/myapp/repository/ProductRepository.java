package com.sk.skala.myapp.repository;

import com.sk.skala.myapp.domain.Product;
import com.sk.skala.myapp.domain.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 상태별 상품 목록 조회
    List<Product> findByStatus(ProductStatus status);

    // 사용자 ID로 상품 목록 조회
    List<Product> findByUserId(Long userId);

    // 사용자 이름으로 상품 목록 조회
    List<Product> findByUserName(String userName);
}