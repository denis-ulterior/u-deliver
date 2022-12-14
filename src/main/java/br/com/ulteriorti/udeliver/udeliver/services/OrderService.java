package br.com.ulteriorti.udeliver.udeliver.services;

import br.com.ulteriorti.udeliver.udeliver.dto.OrderDTO;
import br.com.ulteriorti.udeliver.udeliver.dto.ProductDTO;
import br.com.ulteriorti.udeliver.udeliver.entities.Order;
import br.com.ulteriorti.udeliver.udeliver.entities.OrderStatus;
import br.com.ulteriorti.udeliver.udeliver.entities.Product;
import br.com.ulteriorti.udeliver.udeliver.repositories.OrderRepository;
import br.com.ulteriorti.udeliver.udeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;
    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = repository.findOrdersWithProducts();
        return list.stream().map(item -> new OrderDTO(item)).collect(Collectors.toList());
    }

    @Transactional
    @PreAuthorize("hasRole('USER')")
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
        for (ProductDTO p : dto.getProducts()) {
            Product product = productRepository.getReferenceById(p.getId());
            order.getProducts().add(product);
        }
        order = repository.save(order);
        return new OrderDTO(order);
    }
    @Transactional
    public OrderDTO setDelivered(Long id) {
         Order order = repository.getReferenceById(id);
         order.setStatus(OrderStatus.DELIVERED);
         order = repository.save(order);
         return new OrderDTO(order);
    }
}
