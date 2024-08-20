package shopping.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.domain.entity.Name;

import static org.assertj.core.api.Assertions.assertThatNoException;

class NameTest {
    @Test
    @DisplayName("이름은 생성할 때 예외가 발생하지 않는다.")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new Name("name"));
    }
}
