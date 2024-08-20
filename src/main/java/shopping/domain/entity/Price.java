package shopping.domain.entity;

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
}
