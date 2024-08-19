package shopping.domain;

import java.util.regex.Pattern;

public class ImageUrl {
    public static final String regex = "^https?:\\/\\/.*\\.(png|jpg|jpeg|gif|bmp|svg|webp)$";

    public static final Pattern pattern = Pattern.compile(regex);

    private String value;

    public ImageUrl(String s) throws IllegalArgumentException{
        if(!pattern.matcher(s).matches())
            throw new IllegalArgumentException();

        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
