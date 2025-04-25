package com.developz.inventory.services;

import org.springframework.http.ResponseEntity;

import com.developz.inventory.response.CategoryResponseRest;

public interface ICategoryService {

	public ResponseEntity<CategoryResponseRest> search();
	
}
