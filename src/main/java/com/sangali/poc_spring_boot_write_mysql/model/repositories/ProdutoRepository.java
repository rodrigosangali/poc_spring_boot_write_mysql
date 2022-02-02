package com.sangali.poc_spring_boot_write_mysql.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sangali.poc_spring_boot_write_mysql.model.entities.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {

}
