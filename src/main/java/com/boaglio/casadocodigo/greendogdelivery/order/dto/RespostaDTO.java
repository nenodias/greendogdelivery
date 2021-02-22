package com.boaglio.casadocodigo.greendogdelivery.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RespostaDTO {
    private Double valorTotal;
    private Long pedido;
    private String mensagem;

    public RespostaDTO(Long pedido, Double valorTotal, String mensagem){
        this.pedido = pedido;
        this.valorTotal = valorTotal;
        this.mensagem = mensagem;
    }
}
