package br.com.app.repository;

import br.com.app.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
}