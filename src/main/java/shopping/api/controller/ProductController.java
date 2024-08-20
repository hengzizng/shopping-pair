package shopping.api.controller;

import jakarta.validation.Valid;
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
import shopping.util.IdGenerator;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @PostMapping("")
    public ResponseEntity<ProductInfoResponse> createProduct(
            @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        Product product = new Product(new Name(createProductRequest.getName()), new Price(createProductRequest.getPrice()), new ImageUrl(createProductRequest.getImageUrl()));
        Product savedProduct = productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductInfoResponse(
                savedProduct.getId(),
                savedProduct.getName().getValue(),
                savedProduct.getPrice().getValue(),
                savedProduct.getImageUrl().getValue()
        ));
    }


    @GetMapping("")
    public ResponseEntity<List<ProductInfoResponse>> getProduct() {
        final List<ProductInfoResponse> streamResponses = productRepository.findAll()
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
        if(productRepository.findById(productId).isEmpty()) {
            throw new IllegalArgumentException("해당 상품은 없습니다.");
        }

        Product product = new Product(new Name(createProductRequest.getName()), new Price(createProductRequest.getPrice()), new ImageUrl(createProductRequest.getImageUrl()));
        Product savedProduct = productRepository.save(product);

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
        if(productRepository.findById(productId).isEmpty()) {
            throw new IllegalArgumentException("해당 상품은 없습니다.");
        }

        productRepository.removeById(productId);
    }
}
