package com.developz.inventory.services;

import org.springframework.http.ResponseEntity;

import com.developz.inventory.model.Category;
import com.developz.inventory.response.CategoryResponseRest;

public interface ICategoryService {

	public ResponseEntity<CategoryResponseRest> search();
	
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	
	public ResponseEntity<CategoryResponseRest> save(Category category);
	
}
