package com.bolsaideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsaideas.springboot.app.models.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>,PagingAndSortingRepository<Cliente, Long>{


}
