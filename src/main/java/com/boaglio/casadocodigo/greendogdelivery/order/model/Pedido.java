package com.boaglio.casadocodigo.greendogdelivery.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    private Cliente cliente;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private List<Item> itens;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date data;

    @Min(1)
    private Double valorTotal;

    public Pedido(Long id, Cliente cliente, List<Item> itens, Double valorTotal) {
        super();
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
        this.data = new Date();
        this.valorTotal = valorTotal;
    }
}
