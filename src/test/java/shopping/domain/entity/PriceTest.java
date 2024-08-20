package shopping.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.domain.entity.Price;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThat;

class PriceTest {
    @Test
    @DisplayName("상품 가격은 0보다 크거나 같아야 한다.")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new Price(0));
    }

    @Test
    @DisplayName("상품 가격은 음수면 예외가 발생한다.")
    void constructorExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Price(-1));
    }
}
