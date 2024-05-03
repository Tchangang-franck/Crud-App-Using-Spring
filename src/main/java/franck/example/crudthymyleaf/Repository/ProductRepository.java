package franck.example.crudthymyleaf.Repository;

import franck.example.crudthymyleaf.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product ,Integer> {
}
