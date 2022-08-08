package br.com.ulteriorti.udeliver.udeliver.services;

import br.com.ulteriorti.udeliver.udeliver.dto.ProductDTO;
import br.com.ulteriorti.udeliver.udeliver.entities.Product;
import br.com.ulteriorti.udeliver.udeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> list = repository.findAllByOrderByNameAsc();
        return list.stream().map(item -> new ProductDTO(item)).collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product(null, dto.getName(), dto.getPrice(), dto.getDescription(), dto.getImageUri());
        product = repository.save(product);
        return new ProductDTO(product);
    }
}
