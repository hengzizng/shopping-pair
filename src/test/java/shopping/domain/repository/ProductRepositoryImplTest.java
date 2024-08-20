package shopping.domain.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.domain.entity.ImageUrl;
import shopping.domain.entity.Name;
import shopping.domain.entity.Price;
import shopping.domain.entity.Product;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryImplTest {
    private ProductRepository productRepository = new ProductRepositoryImpl();

    @Test
    @DisplayName("save test")
    void saveTest() {
        상품_생성();
    }

    private Product 상품_생성() {
        Product product = new Product("name", 1000, "https://www.naver.com/image.png");
        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(product.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(savedProduct.getImageUrl()).isEqualTo(product.getImageUrl());

        return savedProduct;
    }

    @Test
    @DisplayName("findByIdTest")
    void findByIdTest() {
        // given
        Product expected = 상품_생성();
        Optional<Product> actual = productRepository.findById(expected.getId());

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getId()).isEqualTo(expected.getId());
        assertThat(actual.get().getName()).isEqualTo(expected.getName());
        assertThat(actual.get().getPrice()).isEqualTo(expected.getPrice());
        assertThat(actual.get().getImageUrl()).isEqualTo(expected.getImageUrl());
    }
}