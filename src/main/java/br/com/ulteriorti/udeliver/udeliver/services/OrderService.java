package br.com.ulteriorti.udeliver.udeliver.services;

import br.com.ulteriorti.udeliver.udeliver.dto.OrderDTO;
import br.com.ulteriorti.udeliver.udeliver.entities.Order;
import br.com.ulteriorti.udeliver.udeliver.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        List<Order> list = repository.findOrdersWithProducts();
        return list.stream().map(item ->new OrderDTO(item)).collect(Collectors.toList());
    }
}
