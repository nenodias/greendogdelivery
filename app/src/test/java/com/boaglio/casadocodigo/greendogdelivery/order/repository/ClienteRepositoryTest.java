package com.boaglio.casadocodigo.greendogdelivery.order.repository;

import com.boaglio.casadocodigo.greendogdelivery.order.model.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void buscaCLientesCadastrados(){
        Page<Cliente> clientes = clienteRepository.findAll(PageRequest.of(0, 10));
        assertThat(clientes.getTotalElements()).isGreaterThan(1L);
    }

    @Test
    public void buscarClienteFernando(){
        Cliente clienteNaoEncontrado = clienteRepository.findByNome("Fernando");
        assertThat(clienteNaoEncontrado).isNull();

        Cliente cliente = clienteRepository.findByNome("Fernando Boaglio");
        assertThat(cliente).isNotNull();
        assertThat(cliente.getNome()).isEqualTo("Fernando Boaglio");
        assertThat(cliente.getEndereco()).isEqualTo("Sampa");
    }

}
