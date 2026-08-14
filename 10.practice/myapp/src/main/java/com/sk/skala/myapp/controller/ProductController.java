package com.sk.skala.myapp.controller;

import com.sk.skala.myapp.domain.Product;
import com.sk.skala.myapp.domain.ProductStatus;
import com.sk.skala.myapp.dto.ProductRequest;
import com.sk.skala.myapp.dto.ProductResponse;
import com.sk.skala.myapp.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 전체 상품 조회
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ID로 상품 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id
    ) {
        return productService.getProductById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 상품 상태별 조회
    // GET /api/products/status?value=ON_SALE
    @GetMapping("/status")
    public List<ProductResponse> getProductsByStatus(
            @RequestParam ProductStatus value
    ) {
        return productService.getProductsByStatus(value)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // 사용자 ID 기준 상품 조회
    // GET /api/products/user?userId=123
    @GetMapping(value = "/user", params = "userId")
    public List<ProductResponse> getProductsByUserId(
            @RequestParam Long userId
    ) {
        return productService.getProductsByUser_Id(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // 사용자 이름 기준 상품 조회
    // GET /api/products/user?name=홍길동
    @GetMapping(value = "/user", params = "name")
    public List<ProductResponse> getProductsByUserName(
            @RequestParam("name") String name
    ) {
        System.out.println("받은 name = [" + name + "]");
        return productService.getProductsByUser_Name(name)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // 상품 등록
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request
    ) {
        Product product = toEntity(request);
        Product savedProduct = productService.createProduct(product);

        return ResponseEntity.ok(toResponse(savedProduct));
    }

    // 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ) {
        Product updatedProduct = toEntity(request);

        return productService.updateProduct(id, updatedProduct)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // ProductRequest DTO → Product Entity
    private Product toEntity(ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setStatus(request.getStatus());
        product.setDescription(request.getDescription());

        return product;
    }

    // Product Entity → ProductResponse DTO
    private ProductResponse toResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setStatus(product.getStatus());
        response.setDescription(product.getDescription());
        response.setDisplayLabel(product.getDisplayLabel());

        if (product.getUser() != null) {
            response.setUserId(product.getUser().getId());
            response.setUserName(product.getUser().getName());
        }

        return response;
    }
}