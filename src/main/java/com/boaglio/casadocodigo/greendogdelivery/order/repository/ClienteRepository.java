package com.boaglio.casadocodigo.greendogdelivery.order.repository;

import com.boaglio.casadocodigo.greendogdelivery.order.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "clientes", path = "clientes")
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
