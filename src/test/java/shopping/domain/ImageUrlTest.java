package shopping.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.domain.entity.ImageUrl;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class ImageUrlTest {
    @Test
    @DisplayName("상품 ImageUrl은 http://나 https://로 시작하고 이미지 확장자로 끝나야 한다. (.png, ~~~)")
    public void ConstructorTest() {

        assertThatIllegalArgumentException().isThrownBy(() -> new ImageUrl("www.naver.com"));
    }

    @Test
    @DisplayName("상품 ImageUrl은 http://나 https://로 시작하고 이미지 확장자로 끝나야 한다. (.png, ~~~)")
    public void ConstructorTest2() {

        assertThatNoException().isThrownBy(() -> new ImageUrl("https://www.naver.com/image.png"));
    }



}
