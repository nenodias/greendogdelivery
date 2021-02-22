package com.boaglio.casadocodigo.greendogdelivery.order.schema;

import com.boaglio.casadocodigo.greendogdelivery.order.model.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.order.model.Item;
import com.boaglio.casadocodigo.greendogdelivery.order.model.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.order.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryTest implements ApplicationRunner {

    private static final long ID_CLIENTE_FERNANDO = 11l;
    private static final long ID_CLIENTE_ZE_PEQUENO = 22l;

    private static final long ID_ITEM1 = 100l;
    private static final long ID_ITEM2 = 101l;
    private static final long ID_ITEM3 = 102l;

    private static final long ID_PEDIDO1 = 1000l;
    private static final long ID_PEDIDO2 = 1001l;
    private static final long ID_PEDIDO3 = 1002l;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println(">>> Iniciando carga de dados...");
        Cliente fernando = new Cliente(ID_CLIENTE_FERNANDO, "Fernando Boaglio", "Sampa");
        Cliente zePequeno = new Cliente(ID_CLIENTE_ZE_PEQUENO, "Zé Pequeno", "Cidade de Deus");

        Item dog1 = new Item(ID_ITEM1, "Green Dog tradicional", 25d);
        Item dog2 = new Item(ID_ITEM2, "Green Dog tradicional picante", 27d);
        Item dog3 = new Item(ID_ITEM3, "Green Dog max salada", 30d);

        List<Item> listaPedidoFernandoItens = new ArrayList<>();
        List<Item> listaPedidoFernando2Itens = new ArrayList<>();
        List<Item> listaPedidoZePequenoItens = new ArrayList<>();
        listaPedidoFernandoItens.add(dog1);
        listaPedidoFernando2Itens.add(dog2);
        listaPedidoZePequenoItens.add(dog2);
        listaPedidoZePequenoItens.add(dog3);

        Pedido pedidoFernando = new Pedido(ID_PEDIDO1, fernando, listaPedidoFernandoItens, dog1.getPreco());
        fernando.novoPedido(pedidoFernando);
        System.out.println(">>> Pedido 1");
        Pedido pedidoZe = new Pedido(ID_PEDIDO2, zePequeno, listaPedidoZePequenoItens, dog2.getPreco() + dog3.getPreco());
        zePequeno.novoPedido(pedidoZe);
        System.out.println(">>> Pedido 2");

        System.out.println(">>> Gravando cliente 1");
        clienteRepository.saveAndFlush(fernando);
        System.out.println(">>> Gravando cliente 2");
        clienteRepository.saveAndFlush(zePequeno);
        System.out.println(">>> Pedido 3");
        Pedido pedidoFernando2 = new Pedido(ID_PEDIDO3, fernando, listaPedidoFernando2Itens, dog2.getPreco());
        fernando.novoPedido(pedidoFernando2);
        System.out.println(">>> Gravando cliente 1");
        clienteRepository.saveAndFlush(fernando);

    }
}
