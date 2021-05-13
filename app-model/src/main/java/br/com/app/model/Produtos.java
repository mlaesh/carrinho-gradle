package br.com.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@ToString(exclude= {"id", "precos"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produtos {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include @Getter @Setter private Long id;

    @NotEmpty
    @Size (min = 3, max = 50)
    @Getter @Setter private String titulo;

    @NotEmpty
    @Size(min = 3, max = 500)
    @Getter @Setter private String descricao;

    @NotNull
    @Getter @Setter private Integer paginas;

    @ElementCollection
    @Getter @Setter private List<Precos> precos;


    public BigDecimal precoPara(TipoPreco tipoPreco) {
        return precos.stream().filter(preco -> preco.getTipo().equals(tipoPreco)).findFirst()
                .get().getValor();
    }
}
