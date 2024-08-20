package shopping.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import shopping.domain.entity.Name;
import shopping.domain.test.fixture.ProductTestFixture;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class NameTest {
    @ParameterizedTest
    @DisplayName("이름은 생성할 때 예외가 발생하지 않는다.")
    @MethodSource("nameStringParameters")
    void constructorTest(final String value) {
        assertThatNoException().isThrownBy(() -> new Name(value));
    }

    private static Stream<Arguments> nameStringParameters() {
        return ProductTestFixture.getNameStrings(10000)
                .stream()
                .map(Arguments::of);
    }

    @Test
    @DisplayName("상품 이름은 15자 이하 및 특정 특수문자만 허용한다.")
    void newConstructorTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Name("abcdefg@naver.com"));
    }
}
