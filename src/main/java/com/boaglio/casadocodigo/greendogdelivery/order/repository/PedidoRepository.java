package com.boaglio.casadocodigo.greendogdelivery.order.repository;

import com.boaglio.casadocodigo.greendogdelivery.order.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
