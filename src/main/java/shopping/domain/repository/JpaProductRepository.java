package shopping.domain.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.domain.entity.Product;

@Repository
@Primary
public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, Long> {
}
