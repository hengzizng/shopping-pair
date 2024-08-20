package shopping.domain.entity;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    public static final String regex = "^[\\p{L}\\p{N}\\s\\(\\)\\[\\]\\+\\-\\&\\/_]{1,15}";
    public static final Pattern pattern = Pattern.compile(regex);
    
    private String value;

    protected Name() {

    }
    

    public String getValue() {
        return value;
    }

    public Name(String s) throws IllegalArgumentException{
        if(!pattern.matcher(s).matches())
            throw new IllegalArgumentException();

        this.value = s;
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
