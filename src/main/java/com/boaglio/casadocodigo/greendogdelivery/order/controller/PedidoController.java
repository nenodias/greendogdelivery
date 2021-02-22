package com.boaglio.casadocodigo.greendogdelivery.order.controller;

import com.boaglio.casadocodigo.greendogdelivery.order.model.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.order.model.Item;
import com.boaglio.casadocodigo.greendogdelivery.order.model.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.order.repository.ClienteRepository;
import com.boaglio.casadocodigo.greendogdelivery.order.repository.ItemRepository;
import com.boaglio.casadocodigo.greendogdelivery.order.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {


    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private final String ITEM_URI = "pedidos/";

    @GetMapping("/")
    public ModelAndView list() {
        Iterable<Pedido> pedidos = this.pedidoRepository.findAll();
        return new ModelAndView(ITEM_URI + "list","pedidos",pedidos);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Pedido pedido) {
        return new ModelAndView(ITEM_URI + "view","pedido",pedido);
    }

    @GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Pedido pedido) {

        Map<String,Object> model = new HashMap<String,Object>();
        model.put("todosItens",itemRepository.findAll());
        model.put("todosClientes",clienteRepository.findAll());
        return new ModelAndView(ITEM_URI + "form",model);

    }

    @PostMapping(params = "form")
    public ModelAndView create(@Valid Pedido pedido, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) { return new ModelAndView(ITEM_URI + "form","formErrors",result.getAllErrors()); }

        if (pedido.getId() != null) {

            Optional<Pedido> pedidoParaAlterarOpt = pedidoRepository.findById(pedido.getId());
            Pedido pedidoParaAlterar = pedidoParaAlterarOpt.get();

            Optional<Cliente> clienteOpt = clienteRepository.findById(pedidoParaAlterar.getCliente().getId());
            Cliente c = clienteOpt.get();

            pedidoParaAlterar.setItens(pedido.getItens());
            double valorTotal = 0;
            for (Item i : pedido.getItens()) {
                valorTotal += i.getPreco();
            }
            pedidoParaAlterar.setData(pedido.getData());
            pedidoParaAlterar.setValorTotal(valorTotal);
            c.getPedidos().remove(pedidoParaAlterar);
            c.getPedidos().add(pedidoParaAlterar);
            this.clienteRepository.save(c);
        } else {

            Optional<Cliente> clienteOpt = clienteRepository.findById(pedido.getCliente().getId());
            Cliente c = clienteOpt.get();

            double valorTotal = 0;
            for (Item i : pedido.getItens()) {
                valorTotal +=i.getPreco();
            }
            pedido.setValorTotal(valorTotal);
            pedido = this.pedidoRepository.save(pedido);
            c.getPedidos().add(pedido);
            this.clienteRepository.save(c);
            // atualiza estoque
//            atualizaEstoque.processar(pedido);
        }
        redirect.addFlashAttribute("globalMessage","Pedido gravado com sucesso");
        return new ModelAndView("redirect:/" + ITEM_URI + "{pedido.id}","pedido.id",pedido.getId());
    }

    @GetMapping(value = "remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id,RedirectAttributes redirect) {

        Optional<Pedido> pedidoParaRemoverOpt = pedidoRepository.findById(id);
        Pedido pedidoParaRemover = pedidoParaRemoverOpt.get();

        Optional<Cliente> clienteOpt = clienteRepository.findById(pedidoParaRemover.getCliente().getId());
        Cliente c = clienteOpt.get();

        this.clienteRepository.save(c);
        this.pedidoRepository.deleteById(id);

        Iterable<Pedido> pedidos = this.pedidoRepository.findAll();

        ModelAndView mv = new ModelAndView(ITEM_URI + "list","pedidos",pedidos);
        mv.addObject("globalMessage","Pedido removido com sucesso");

        return mv;
    }

    @GetMapping(value = "alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Pedido pedido) {

        Map<String,Object> model = new HashMap<String,Object>();
        model.put("todosItens",itemRepository.findAll());
        model.put("todosClientes",clienteRepository.findAll());
        model.put("pedido",pedido);

        return new ModelAndView(ITEM_URI + "form",model);
    }

}
