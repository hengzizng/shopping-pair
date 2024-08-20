package shopping.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.domain.entity.Product;
import shopping.domain.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    public Product updateProduct(Product updateProduct) {
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
}
