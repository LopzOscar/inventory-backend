package com.developz.inventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.developz.inventory.dao.ICategoryDao;
import com.developz.inventory.model.Category;
import com.developz.inventory.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {
		CategoryResponseRest resp = new CategoryResponseRest();
		
		try {
			
			List<Category> category = (List<Category>) categoryDao.findAll();
			
			resp.getCategoryResponse().setCategory(category);
			resp.setMetadata("Respuesta Exitosa", "00", "Respuesta Ok");
			
		} catch (Exception e) {
			resp.setMetadata("Respuesta nok", "-1", "error al realizar la consulta");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.OK);
	}

	
	
}
