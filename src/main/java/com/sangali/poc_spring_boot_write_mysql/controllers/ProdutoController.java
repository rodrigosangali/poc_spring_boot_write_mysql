package com.sangali.poc_spring_boot_write_mysql.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sangali.poc_spring_boot_write_mysql.model.repositories.ProdutoRepository;

import com.sangali.poc_spring_boot_write_mysql.model.entities.Produto;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//Grava um novo produto no post e altera no put
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody Produto novoProduto(Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}
		
	//Consulta de todos os produtos
	@GetMapping
	public Iterable<Produto> obterProdutos(){
		return produtoRepository.findAll();
	}
	
	// Paginação
	@GetMapping(path = "/pagina/{numeroPagina}")
	public Iterable<Produto> obterProdutoPorPagina(@PathVariable int numeroPagina){
		Pageable page = PageRequest.of(numeroPagina, 5);
		return produtoRepository.findAll(page);
	}
	//http://localhost:8080/api/produtos/pagina/0/3
	// Paginação e quantidade de pagina
	@GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
	public Iterable<Produto> obterProdutoPorPaginaEQtde(
			@PathVariable int numeroPagina, 
			@PathVariable int qtdePagina){
		if (qtdePagina >=5) qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return produtoRepository.findAll(page);
	}
	
	
	// Consulta por ID
	//http://localhost:8080/api/produtos/1
	@GetMapping(path="/{id}")
	public Optional<Produto> obterProdutoPorId(@PathVariable int id) {
		return produtoRepository.findById(id);
	}

	// Apagar um produto
	@DeleteMapping(path="/{id}")
	public void excluirProduto(@PathVariable int id) {
		produtoRepository.deleteById(id);
	}

}
