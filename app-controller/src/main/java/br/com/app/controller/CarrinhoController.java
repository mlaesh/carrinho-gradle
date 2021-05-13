package br.com.app.controller;

import br.com.app.model.CarrinhoCompras;
import br.com.app.model.CarrinhoItem;
import br.com.app.model.Produtos;
import br.com.app.model.TipoPreco;
import br.com.app.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/carrinho")
//@Scope(value= WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {

    @Autowired
    private ProdutosRepository repository;

    @Autowired
    private CarrinhoCompras carrinhoCompras;



    @RequestMapping("/add")
    public void add(Long produtoId, TipoPreco tipoPreco) {
        CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
        carrinhoCompras.add(carrinhoItem);
    }
    private CarrinhoItem criaItem(Long produtoId, TipoPreco tipoPreco) {
        Optional<Produtos> produto = repository.findById(produtoId);
        CarrinhoItem carrinhoItem = new CarrinhoItem(produto.get(), tipoPreco);
        return carrinhoItem;
    }

    @DeleteMapping
    public void delete(Long id, TipoPreco tipoPreco){
        carrinhoCompras.remover(id, tipoPreco);
    }
}
