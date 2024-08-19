package shopping.domain;

public class Product {

    private Name name;
    private Price price;
    private ImageUrl imageUrl;

    public Product(Name name, Price price, ImageUrl imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
