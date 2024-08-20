package shopping.domain.entity;

import java.util.Objects;
import java.util.regex.Pattern;

public class ImageUrl {
    public static final String regex = "^https?:\\/\\/.*\\.(png|jpg|jpeg|gif|bmp|svg|webp)$";

    public static final Pattern pattern = Pattern.compile(regex);

    private String value;

    protected ImageUrl() {

    }

    public ImageUrl(String s) throws IllegalArgumentException{
        if(!pattern.matcher(s).matches())
            throw new IllegalArgumentException();

        this.value = s;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageUrl imageUrl)) return false;
        return Objects.equals(getValue(), imageUrl.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
