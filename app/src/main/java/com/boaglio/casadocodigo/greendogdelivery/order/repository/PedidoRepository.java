package com.boaglio.casadocodigo.greendogdelivery.order.repository;

import com.boaglio.casadocodigo.greendogdelivery.order.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pedidos", path = "pedidos")
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
