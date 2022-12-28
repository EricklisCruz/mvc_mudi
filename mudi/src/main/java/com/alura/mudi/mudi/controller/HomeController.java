package com.alura.mudi.mudi.controller;

import com.alura.mudi.mudi.model.Pedido;
import com.alura.mudi.mudi.model.StatusPedido;
import com.alura.mudi.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    private String home(Model model) {
        Sort sort = Sort.by("deliveryDate").descending(); //ORDENAÇÃO DA DATA MAIS ATUAL PARA A ANTIGA
        PageRequest paginacao = PageRequest.of(0,10, sort);
        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
        model.addAttribute("pedidos",pedidos);
        return "home";
    }
}
