package shopping.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import shopping.api.request.CreateProductRequest;
import shopping.domain.client.SwearWordFilterClient;
import shopping.domain.entity.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ProductServiceMockTest {
    @MockBean
    private SwearWordFilterClient swearWordFilterClient;

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("createProductTest")
    void createProductTest() {
        given(swearWordFilterClient.isNotCleanText(any())).willReturn(true);

        assertThatIllegalArgumentException().isThrownBy(() -> 상품_생성("책",5000,"https://image.png"));
    }

    private Product 상품_생성(
            String name,
            int price,
            String imageUrl
    ) {
        CreateProductRequest product = new CreateProductRequest(name, price, imageUrl);
        Product savedProduct = productService.createProduct(product);

        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName().getValue()).isEqualTo(product.getName());
        assertThat(savedProduct.getPrice().getValue()).isEqualTo(product.getPrice());
        assertThat(savedProduct.getImageUrl().getValue()).isEqualTo(product.getImageUrl());

        return savedProduct;
    }

    @Test
    @DisplayName("updateProductTest")
    void updateProductTest() {
        Product savedProduct = 상품_생성("책",5000,"https://image.png");
        Product updateProduct = new Product(savedProduct.getId(), "수정", 2000, "https://naver.png");

        given(swearWordFilterClient.isNotCleanText(any())).willReturn(true);

        assertThatIllegalArgumentException().isThrownBy(() -> productService.updateProduct(updateProduct));
    }

    @Test
    @DisplayName("deleteProductTest")
    void deleteProductTest() {
        Product savedProduct = 상품_생성("책",5000,"https://image.png");

        assertThatNoException().isThrownBy(() -> productService.deleteProduct(savedProduct.getId()));
    }

    @Test
    @DisplayName("findAllTest")
    void findAllTest() {
        Product savedProduct = 상품_생성("책",5000,"https://image.png");

        List<Product> actual = productService.findAll();

        assertThat(actual.get(0).getId()).isNotNull();
        assertThat(actual.get(0).getName()).isEqualTo(savedProduct.getName());
        assertThat(actual.get(0).getPrice()).isEqualTo(savedProduct.getPrice());
        assertThat(actual.get(0).getImageUrl()).isEqualTo(savedProduct.getImageUrl());
    }

}
