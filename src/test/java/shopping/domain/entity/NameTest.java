package shopping.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.domain.entity.Name;
import shopping.domain.test.fixture.ProductTestFixture;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class NameTest {
    @Test
    @DisplayName("이름은 생성할 때 예외가 발생하지 않는다.")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new Name(ProductTestFixture.getName().getValue()));
    }

    @Test
    @DisplayName("상품 이름은 15자 이하 및 특정 특수문자만 허용한다.")
    void newConstructorTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Name("abcdefg@naver.com"));
    }
}
