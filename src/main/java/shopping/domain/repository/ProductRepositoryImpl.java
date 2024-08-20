package shopping.domain.repository;

import shopping.domain.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepositoryImpl implements ProductRepository {
    private final TreeMap<Long, Product> dataBase = new TreeMap<>();
    private final AtomicLong id = new AtomicLong(0L);

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(dataBase.get(id));
    }

    @Override
    public List<Product> findAll() {
        return dataBase.values().stream().toList();
    }

    @Override
    public Product save(Product product) {
        Product newProduct = new Product(id.getAndIncrement(), product);

        dataBase.put(newProduct.getId(), newProduct);

        return newProduct;
    }

    @Override
    public void removeById(long productId) {
        dataBase.remove(productId);
    }
}
