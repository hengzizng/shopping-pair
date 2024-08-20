package shopping.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shopping.domain.entity.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("createProductTest")
    void createProductTest() {
        Product product = new Product("책",5000,"https://image.png");

        Product savedProduct = productService.createProduct(product);

        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(product.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(savedProduct.getImageUrl()).isEqualTo(product.getImageUrl());

    }

    @Test
    @DisplayName("updateProductTest")
    void updateProductTest() {
        Product product = new Product(1L,"책",5000,"https://image.png");

        Product savedProduct = productService.createProduct(product);
        Product updateProduct = new Product(savedProduct.getId(), "수정", 2000, "https://naver.png");

        Product actual = productService.updateProduct(updateProduct);

        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo(updateProduct.getName());
        assertThat(actual.getPrice()).isEqualTo(updateProduct.getPrice());
        assertThat(actual.getImageUrl()).isEqualTo(updateProduct.getImageUrl());
    }

    @Test
    @DisplayName("deleteProductTest")
    void deleteProductTest() {
        Product product = new Product(1L,"책",5000,"https://image.png");

        Product savedProduct = productService.createProduct(product);

        assertThatNoException().isThrownBy(() -> productService.deleteProduct(savedProduct.getId()));
    }

    @Test
    @DisplayName("findAllTest")
    void findAllTest() {
        Product product = new Product(1L,"책",5000,"https://image.png");

        Product savedProduct = productService.createProduct(product);

        List<Product> actual = productService.findAll();

        assertThat(actual.get(0).getId()).isNotNull();
        assertThat(actual.get(0).getName()).isEqualTo(savedProduct.getName());
        assertThat(actual.get(0).getPrice()).isEqualTo(savedProduct.getPrice());
        assertThat(actual.get(0).getImageUrl()).isEqualTo(savedProduct.getImageUrl());
    }



}
