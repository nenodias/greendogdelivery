package com.boaglio.casadocodigo.greendogdelivery.order.repository;

import com.boaglio.casadocodigo.greendogdelivery.order.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
