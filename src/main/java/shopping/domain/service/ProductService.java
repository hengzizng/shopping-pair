package shopping.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import shopping.api.request.CreateProductRequest;
import shopping.domain.client.SwearWordFilterClient;
import shopping.domain.entity.ImageUrl;
import shopping.domain.entity.Name;
import shopping.domain.entity.Price;
import shopping.domain.entity.Product;
import shopping.domain.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SwearWordFilterClient swearWordFilterClient;

    public Product createProduct(CreateProductRequest createProductRequest) {
        if(swearWordFilterClient.isNotCleanText(createProductRequest.getName())) {
            throw new IllegalArgumentException("욕설은 포함할 수 없습니다.");
        }
        Product product = new Product(new Name(createProductRequest.getName()), new Price(createProductRequest.getPrice()), new ImageUrl(createProductRequest.getImageUrl()));
        return productRepository.save(product);
    }

    public Product updateProduct(Product updateProduct) {
        if(swearWordFilterClient.isNotCleanText(updateProduct.getName().getValue())) {
            throw new IllegalArgumentException("욕설은 포함할 수 없습니다.");
        }
        if(productRepository.findById(updateProduct.getId()).isEmpty()) {
            throw new IllegalArgumentException("해당 상품이 없습니다.");
        }

        return productRepository.save(updateProduct);
    }

    public void deleteProduct(Long id) {
        if(productRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("해당 상품이 없습니다.");
        }

        productRepository.removeById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void a() {

    }
}
