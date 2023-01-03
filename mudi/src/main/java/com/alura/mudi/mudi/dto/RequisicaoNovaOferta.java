package com.alura.mudi.mudi.dto;

import com.alura.mudi.mudi.model.Oferta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class RequisicaoNovaOferta {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Long pedidoId;
    @Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
    @NotBlank
    private String definedValue;
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
    @NotBlank
    private String deliveryDate;
    private String coments;

    public Oferta toOferta() {
        Oferta oferta = new Oferta();
        oferta.setComents(this.coments);
        oferta.setDeliveryDate(LocalDate.parse(this.deliveryDate, FORMATTER));
        oferta.setDefinedValue((new BigDecimal(this.definedValue)));
        return oferta;
    }
}
