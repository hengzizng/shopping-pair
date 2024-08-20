package shopping.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.api.request.CreateProductRequest;
import shopping.api.response.ProductInfoResponse;
import shopping.domain.entity.ImageUrl;
import shopping.domain.entity.Name;
import shopping.domain.entity.Price;
import shopping.domain.entity.Product;
import shopping.domain.repository.ProductRepository;
import shopping.domain.repository.ProductRepositoryImpl;
import shopping.domain.service.ProductService;
import shopping.util.IdGenerator;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<ProductInfoResponse> createProduct(
            @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        Product savedProduct = productService.createProduct(createProductRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductInfoResponse(
                savedProduct.getId(),
                savedProduct.getName().getValue(),
                savedProduct.getPrice().getValue(),
                savedProduct.getImageUrl().getValue()
        ));
    }


    @GetMapping("")
    public ResponseEntity<List<ProductInfoResponse>> getProduct() {
        final List<ProductInfoResponse> streamResponses = productService.findAll()
                .stream()
                .map(product -> new ProductInfoResponse(
                        product.getId(),
                        product.getName().getValue(),
                        product.getPrice().getValue(),
                        product.getImageUrl().getValue()
                )).toList();

        return ResponseEntity.status(HttpStatus.OK).body(streamResponses);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProductInfoResponse> updateProduct(
            @PathVariable long productId,
            @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        Product product = new Product(productId, createProductRequest.getName(), createProductRequest.getPrice(), createProductRequest.getImageUrl());
        Product savedProduct = productService.updateProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductInfoResponse(
                productId,
                savedProduct.getName().getValue(),
                savedProduct.getPrice().getValue(),
                savedProduct.getImageUrl().getValue()
        ));
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(
            @PathVariable long productId
    ) {
        productService.deleteProduct(productId);
    }
}
