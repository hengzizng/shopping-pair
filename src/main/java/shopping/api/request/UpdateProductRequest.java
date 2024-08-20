package shopping.api.request;

import jakarta.validation.constraints.Pattern;
import shopping.domain.ImageUrl;

public class UpdateProductRequest {
    private Long id;

    private String name;

    private int price;

    @Pattern(regexp = ImageUrl.regex)
    private String imageUrl;

    public UpdateProductRequest(Long id, String 수정, int i, String url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
