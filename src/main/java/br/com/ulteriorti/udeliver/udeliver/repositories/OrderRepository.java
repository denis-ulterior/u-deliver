package br.com.ulteriorti.udeliver.udeliver.repositories;

import br.com.ulteriorti.udeliver.udeliver.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Long> {
}
