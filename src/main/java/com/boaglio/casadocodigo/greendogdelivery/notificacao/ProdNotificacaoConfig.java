package com.boaglio.casadocodigo.greendogdelivery.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdNotificacaoConfig implements Notificacao {

    @Override
    public boolean envioAtivo() {
        return true;
    }
}
