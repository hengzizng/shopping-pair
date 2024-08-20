package shopping.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.api.request.CreateProductRequest;
import shopping.api.response.ProductInfoResponse;
import shopping.domain.ImageUrl;
import shopping.domain.Name;
import shopping.domain.Price;
import shopping.domain.Product;
import shopping.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final TreeMap<Long, Product> dataBase = new TreeMap<>();

    @PostMapping("")
    public ResponseEntity<ProductInfoResponse> createProduct(
            @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        Product product = new Product(new Name(createProductRequest.getName()), new Price(createProductRequest.getPrice()), new ImageUrl(createProductRequest.getImageUrl()));
        Long id = IdGenerator.getNextId(dataBase);
        dataBase.put(id, product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductInfoResponse(
                id,
                product.getName().getValue(),
                product.getPrice().getValue(),
                product.getImageUrl().getValue()
        ));
    }


    @GetMapping("")
    public ResponseEntity<List<ProductInfoResponse>> getProduct() {
        final List<ProductInfoResponse> streamResponses = dataBase.entrySet()
                .stream()
                .map(mapEntry -> new ProductInfoResponse(
                        mapEntry.getKey(),
                        mapEntry.getValue().getName().getValue(),
                        mapEntry.getValue().getPrice().getValue(),
                        mapEntry.getValue().getImageUrl().getValue()
                )).toList();

        return ResponseEntity.status(HttpStatus.OK).body(streamResponses);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProductInfoResponse> updateProduct(
            @PathVariable long productId,
            @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        if(Optional.ofNullable(dataBase.get(productId)).isEmpty()) {
            throw new IllegalArgumentException("해당 상품은 없습니다.");
        }

        Product product = new Product(new Name(createProductRequest.getName()), new Price(createProductRequest.getPrice()), new ImageUrl(createProductRequest.getImageUrl()));
        dataBase.put(productId, product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductInfoResponse(
                productId,
                product.getName().getValue(),
                product.getPrice().getValue(),
                product.getImageUrl().getValue()
        ));
    }

}
