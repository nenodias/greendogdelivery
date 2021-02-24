package com.boaglio.casadocodigo.greendogdelivery.oferta.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MensagemDTO {

    private String mensagem;
    private String servidor;
    private String debug;

    public MensagemDTO(String mensagem,String servidor,String debug){
        this.mensagem = mensagem;
        this.servidor = servidor;
        this.debug = debug;
    }
}
