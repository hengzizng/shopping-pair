package shopping.domain;

public class Price {
    private int value;

    public Price(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }

        this.value = i;
    }
}
