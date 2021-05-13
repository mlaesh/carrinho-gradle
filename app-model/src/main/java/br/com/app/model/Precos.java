package br.com.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter @Setter
public class Precos {

    private BigDecimal valor;
    private TipoPreco tipo;

}
