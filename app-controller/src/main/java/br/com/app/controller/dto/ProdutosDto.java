package br.com.app.controller.dto;

import br.com.app.model.Produtos;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutosDto {

    @Getter @Setter private Long id;
    @Getter @Setter private String titulo;
    @Getter @Setter private String descricao;
    @Getter @Setter private Integer paginas;

    public ProdutosDto(Produtos produtos){
        this.id = produtos.getId();
        this.titulo = produtos.getTitulo();
        this.descricao = produtos.getDescricao();
        this.paginas = produtos.getPaginas();
    }
    public static List<ProdutosDto> converter(List<Produtos> produtos){
        return produtos.stream().map(ProdutosDto::new).collect(Collectors.toList());
    }
}
