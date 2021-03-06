package br.com.app.controller;

import br.com.app.controller.dto.ProdutosDto;
import br.com.app.model.Produtos;
import br.com.app.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosRepository repository;

    @GetMapping
    public ResponseEntity<List<Produtos>> listar(){
        List<Produtos> produtosList = repository.findAll();
        if(produtosList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            for(Produtos produto : produtosList){
                Long id = produto.getId();
                produto.add(linkTo(methodOn(ProdutosController.class).detalhar(id)).withSelfRel());
            }
            return new ResponseEntity<List<Produtos>>(produtosList, HttpStatus.OK);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutosDto> gravar (@RequestBody @Valid Produtos produtos, UriComponentsBuilder uriBuilder) {
        repository.save(produtos);
        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtos.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutosDto(produtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosDto> detalhar(@PathVariable Long id){
        Optional<Produtos> produtos = repository.findById(id);
        if(produtos.isPresent()){
            produtos.get().add(linkTo(methodOn(ProdutosController.class).listar()).withRel("Todos os produtos"));
            return ResponseEntity.ok(new ProdutosDto(produtos.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Produtos> optional = repository.findById(id);
        if(optional.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
