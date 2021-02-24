package com.boaglio.casadocodigo.greendogdelivery.notificacao;

import com.boaglio.casadocodigo.greendogdelivery.order.model.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.order.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class EnviaNotificacao {

    @Autowired
    Notificacao notificacao;

    public void enviaEmail(Cliente cliente, Pedido pedido){
        if(notificacao.envioAtivo()){
            System.out.println("Enviando email de pedido");
        }
        System.out.println(MessageFormat.format("Cliente {0} realizou o pedido {1}", cliente.getNome(), pedido.getId()));
    }

}
