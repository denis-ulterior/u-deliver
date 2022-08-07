package br.com.ulteriorti.udeliver.udeliver.controllers;

import br.com.ulteriorti.udeliver.udeliver.dto.OrderDTO;
import br.com.ulteriorti.udeliver.udeliver.dto.ProductDTO;
import br.com.ulteriorti.udeliver.udeliver.entities.Order;
import br.com.ulteriorti.udeliver.udeliver.services.OrderService;
import br.com.ulteriorti.udeliver.udeliver.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        List<OrderDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
