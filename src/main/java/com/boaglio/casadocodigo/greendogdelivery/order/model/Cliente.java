package com.boaglio.casadocodigo.greendogdelivery.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min=2, max=30, message = "O tamanho do nome deve ser entre {min} e {max} caracteres")
    private String nome;

    @NotNull
    @Length(min=2, max=300, message = "O tamanho do endereço deve ser entre {min} e {max} caracteres")
    private String endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Pedido> pedidos;

    public Cliente(Long id,String nome,String endereco) {
        super();
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public void novoPedido(Pedido pedido) {
        if (this.pedidos==null) pedidos = new ArrayList<Pedido>();
        pedidos.add(pedido);
    }

}
