package shopping.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name"))
    private Name name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price"))
    private Price price;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "image_url"))
    private ImageUrl imageUrl;

    protected Product() {

    }

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
