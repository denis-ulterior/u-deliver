package br.com.ulteriorti.udeliver.udeliver.repositories;

import br.com.ulteriorti.udeliver.udeliver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByNameAsc();
}

