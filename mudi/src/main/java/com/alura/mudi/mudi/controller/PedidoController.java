package com.alura.mudi.mudi.controller;

import com.alura.mudi.mudi.dto.RequisicaoNovoPedido;
import com.alura.mudi.mudi.model.Pedido;
import com.alura.mudi.mudi.model.User;
import com.alura.mudi.mudi.repository.PedidoRepository;
import com.alura.mudi.mudi.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido requisicao) {
        return "pedido/formulario";
    }

    @PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
        if(result.hasErrors()) {
            return "pedido/formulario";
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User usuario = userRepository.findByUsername(username);

        Pedido pedido = requisicao.toPedido();
        pedido.setUser(usuario);
        pedidoRepository.save(pedido);
        return "redirect:/home";
    }
}

