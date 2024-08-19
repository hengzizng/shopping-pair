package shopping.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

class IdGeneratorTest {
    @Test
    @DisplayName("상품 ID는 기본값이 0이다.")
    void getNextIdTest() {
        // given
        final TreeMap<Long, Object> treeMap = new TreeMap<>();
        final Long expected = 0L;

        // when
        final Long actual = IdGenerator.getNextId(treeMap);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("상품 ID는 마지막 값에서 1을 자동 증가시킨다.")
    void getNextIdTest2() {
        // given
        final TreeMap<Long, Object> treeMap = new TreeMap<>();
        final Long lastId = 0L;
        treeMap.put(lastId, new Object());

        final Long expected = lastId + 1;

        // when
        final Long actual = IdGenerator.getNextId(treeMap);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
