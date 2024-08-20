package shopping.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.domain.entity.ImageUrl;
import shopping.domain.entity.Name;
import shopping.domain.entity.Price;
import shopping.domain.entity.Product;

import static org.assertj.core.api.Assertions.assertThatNoException;

class ProductTest {
    @Test
    @DisplayName("상품 생성할 땐 Name, Price, ImageUrl가 필요하다.")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new Product(
                new Name("책"), new Price(5000), new ImageUrl("http://www.naver.com/image.png")

        ));
    }

}
