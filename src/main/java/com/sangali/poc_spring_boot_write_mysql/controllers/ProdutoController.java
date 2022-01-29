package com.sangali.poc_spring_boot_write_mysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sangali.poc_spring_boot_write_mysql.model.repositories.ProdutoRepository;

import com.sangali.poc_spring_boot_write_mysql.model.entities.Produto;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	public @ResponseBody Produto novoProduto(
			@RequestParam String nome,
			@RequestParam Double preco,
			@RequestParam Double desconto) {
		Produto produto = new Produto(nome, preco, desconto);
		produtoRepository.save(produto);
		return produto;
	}

}
