package com.developz.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.developz.inventory.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long> {

	
	
}
