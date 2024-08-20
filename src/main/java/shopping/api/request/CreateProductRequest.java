package shopping.api.request;

import jakarta.validation.constraints.Pattern;
import shopping.domain.entity.ImageUrl;
import shopping.domain.entity.Name;

public class CreateProductRequest {

    @Pattern(regexp = Name.regex)
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
