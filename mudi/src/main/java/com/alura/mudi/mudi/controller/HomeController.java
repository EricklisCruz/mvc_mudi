package com.alura.mudi.mudi.controller;

import com.alura.mudi.mudi.model.Pedido;
import com.alura.mudi.mudi.model.StatusPedido;
import com.alura.mudi.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidoRepository pedidoRepository;

    private String home(Model model) {
        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE);
        model.addAttribute("pedidos",pedidos);
        return "home";
    }
}
