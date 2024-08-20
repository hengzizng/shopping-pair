package shopping.api.request;

import jakarta.validation.constraints.Pattern;
import shopping.domain.entity.ImageUrl;

public class CreateProductRequest {
    private String name;

    private int price;

    @Pattern(regexp = ImageUrl.regex)
    private String imageUrl;

    public CreateProductRequest(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
