package com.boaglio.casadocodigo.greendogdelivery.order.rest;

import com.boaglio.casadocodigo.greendogdelivery.notificacao.EnviaNotificacao;
import com.boaglio.casadocodigo.greendogdelivery.order.dto.RespostaDTO;
import com.boaglio.casadocodigo.greendogdelivery.order.model.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.order.model.Item;
import com.boaglio.casadocodigo.greendogdelivery.order.model.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.order.repository.ClienteRepository;
import com.boaglio.casadocodigo.greendogdelivery.order.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class NovoPedidoController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EnviaNotificacao enviaNotificacao;

    @GetMapping("/rest/pedido/novo/{clienteId}/{listaDeItens}")
    public RespostaDTO novo(@PathVariable("clienteId") Long clienteId, @PathVariable("listaDeItens") String listaDeItens){
        RespostaDTO dto = new RespostaDTO();
        try {
            Cliente cliente = clienteRepository.getOne(clienteId);
            String[] listaDeItensID = listaDeItens.split(",");

            Pedido pedido = new Pedido();
            double valorTotal = 0;
            List<Item> itensPedidos = new ArrayList<>();
            for (String itemId : listaDeItensID) {
                Item item = itemRepository.getOne(Long.parseLong(itemId));
                itensPedidos.add(item);
                valorTotal += item.getPreco();
            }
            pedido.setItens(itensPedidos);
            pedido.setValorTotal(valorTotal);
            pedido.setCliente(cliente);
            cliente.getPedidos().add(pedido);
            this.clienteRepository.saveAndFlush(cliente);
            enviaNotificacao.enviaEmail(cliente, pedido);
            List<Long> pedidosID = new ArrayList<>();
            for (Pedido p : cliente.getPedidos()) {
                pedidosID.add(p.getId());
            }
            Long ultimoPedido = Collections.max(pedidosID);
            dto = new RespostaDTO(ultimoPedido, valorTotal, "Pedido efetuado com sucesso");
        }catch(Exception ex){
            dto.setMensagem("Erro: " + ex.getMessage());
        }
        return dto;
    }
}
