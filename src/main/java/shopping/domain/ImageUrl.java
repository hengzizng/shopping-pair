package shopping.domain;

import java.util.regex.Pattern;

public class ImageUrl {
    public ImageUrl(String s) throws IllegalArgumentException{
        String regex = "^https?:\\/\\/.*\\.(png|jpg|jpeg|gif|bmp|svg|webp)$";

        if(!Pattern.compile(regex).matcher(s).matches())
            throw new IllegalArgumentException();
    }
}
