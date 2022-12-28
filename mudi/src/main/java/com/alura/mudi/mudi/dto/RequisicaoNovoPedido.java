package com.alura.mudi.mudi.dto;

import com.alura.mudi.mudi.model.Pedido;
import com.alura.mudi.mudi.model.StatusPedido;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequisicaoNovoPedido {

    @NotBlank
    private String productName;
    @NotBlank
    private String productUrl;
    @NotBlank
    private String imageUrl;
    private String description;

    public Pedido toPedido(){
        Pedido pedido = new Pedido();
        pedido.setDescription(description);
        pedido.setProductName(productName);
        pedido.setImageUrl(imageUrl);
        pedido.setProductUrl(productUrl);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        return pedido;
    }

}
