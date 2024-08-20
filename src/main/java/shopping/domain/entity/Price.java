package shopping.domain.entity;

import java.util.Objects;

public class Price {
    private int value;

    public Price(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }

        this.value = i;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price price)) return false;
        return getValue() == price.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
