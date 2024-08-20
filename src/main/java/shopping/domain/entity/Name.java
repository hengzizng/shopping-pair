package shopping.domain.entity;

import java.util.Objects;

public class Name {
    public static final String regex = "^[\\p{L}\\p{N}\\s\\(\\)\\[\\]\\+\\-\\&\\/_]{1,15}";

    private String value;

    protected Name() {

    }

    public Name(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name name)) return false;
        return Objects.equals(getValue(), name.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
