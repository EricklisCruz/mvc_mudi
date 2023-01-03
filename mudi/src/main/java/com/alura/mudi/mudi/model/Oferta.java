package com.alura.mudi.mudi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal definedValue;
    private LocalDate deliveryDate;
    private String coments;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Pedido pedido;
}
