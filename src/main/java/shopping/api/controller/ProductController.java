package shopping.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping.api.request.CreateProductRequest;
import shopping.api.response.ProductInfoResponse;
import shopping.domain.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @PostMapping("")
    public ResponseEntity<ProductInfoResponse> createProduct(
            @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductInfoResponse(
                "name",
                1000,
                "https://www.naver.com/image.png"
        ));
    }
}
