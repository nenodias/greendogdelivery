package com.boaglio.casadocodigo.greendogdelivery.order.repository;

import com.boaglio.casadocodigo.greendogdelivery.order.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
