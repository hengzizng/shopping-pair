package shopping.domain.entity;

public class Product {
    private Long id;
    private Name name;
    private Price price;
    private ImageUrl imageUrl;

    public Product(Name name, Price price, ImageUrl imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(String name, int price, String imageUrl) {
        this.name = new Name(name);
        this.price = new Price(price);
        this.imageUrl = new ImageUrl(imageUrl);
    }

    public Product(Long id, Product product) {
        this.id = id;
        this.name = product.name;
        this.price = product.price;
        this.imageUrl = product.imageUrl;
    }

    public Product(long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = new Name(name);
        this.price = new Price(price);
        this.imageUrl = new ImageUrl(imageUrl);
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public ImageUrl getImageUrl() {
        return imageUrl;
    }

    public Price getPrice() {
        return price;
    }
}
