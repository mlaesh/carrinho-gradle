package br.com.app.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarrinhoItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include @Getter
    @Setter private Produtos produto;
    @EqualsAndHashCode.Include @Getter @Setter
    private TipoPreco tipoPreco;

    public BigDecimal getPreco() {
        return produto.precoPara(tipoPreco);
    }

    public BigDecimal getTotal(int quantidade) {
        return this.getPreco().multiply(new BigDecimal(quantidade));
    }

}
