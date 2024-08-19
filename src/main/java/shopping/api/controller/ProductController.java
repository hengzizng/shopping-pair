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
        dataBase.put(IdGenerator.getNextId(dataBase), product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductInfoResponse(
                product.getName().getValue(),
                product.getPrice().getValue(),
                product.getImageUrl().getValue()
        ));
    }


    @GetMapping("")
    public ResponseEntity<List<ProductInfoResponse>> getProduct() {
//        List<ProductInfoResponse> responses = new ArrayList<>();
//
//        for (Product value : dataBase.values()) {
//            responses.add(
//                    new ProductInfoResponse(
//                            value.getName().getValue(),
//                            value.getPrice().getValue(),
//                            value.getImageUrl().getValue()
//                    )
//            );
//        }
        // return ResponseEntity.status(HttpStatus.OK).body(responses);

        final List<ProductInfoResponse> streamResponses = dataBase.values()
                .stream()
                .map(value -> new ProductInfoResponse(
                        value.getName().getValue(),
                        value.getPrice().getValue(),
                        value.getImageUrl().getValue()
                )).toList();

        return ResponseEntity.status(HttpStatus.OK).body(streamResponses);
    }
}
