package com.developz.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
			resp.setMetadata("Respuesta ok", "00", "Respuesta Exitosa");
			
		} catch (Exception e) {
			
			resp.setMetadata("Respuesta nok", "-1", "error al realizar la consulta");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		
		CategoryResponseRest resp = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Optional<Category> category = categoryDao.findById(id);
			
			if(category.isPresent()) {
				
				list.add(category.get());
				resp.getCategoryResponse().setCategory(list);
				resp.setMetadata("Respuesta ok", "00", "Categoría encontrada");
				
			} else {
				
				resp.setMetadata("Respuesta nok", "-2", "Categoría no encontrada");
				return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.NOT_FOUND);
				
			}
			
		} catch (Exception e) {
			
			resp.setMetadata("Respuesta nok", "-1", "error al realizar la consulta por id");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.OK);
	}

	@Override
	@Transactional()
	public ResponseEntity<CategoryResponseRest> save(Category category) {

		CategoryResponseRest resp = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Category categorySaved = categoryDao.save(category);
			
			if(categorySaved != null) {
				
				list.add(categorySaved);
				resp.getCategoryResponse().setCategory(list);
				resp.setMetadata("Respuesta ok", "00", "Categoría guardada");
				
			} else {
				
				resp.setMetadata("Respuesta nok", "-2", "Categoría no guardada");
				return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.BAD_REQUEST);
				
			}
			
		} catch (Exception e) {
			
			resp.setMetadata("Respuesta nok", "-1", "Error al guardar la consulta");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {

		CategoryResponseRest resp = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Optional<Category> categorySearch = categoryDao.findById(id); 
			
			if(categorySearch.isPresent()) {
				
				// se procede a actualizar el registro
				categorySearch.get().setName(category.getName());
				categorySearch.get().setDescription(category.getDescription());

				Category categoryToUpdate = categoryDao.save(categorySearch.get());
				
				if(categoryToUpdate != null) {
					
					list.add(categoryToUpdate);
					resp.getCategoryResponse().setCategory(list);
					resp.setMetadata("Respuesta ok", "00", "Categoría Actualizada");
					
				} else {
					
					// no se pudo actualizar el id, error en los datos
					resp.setMetadata("Respuesta nok", "-2", "Categoría no actualizada");
					return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.BAD_REQUEST);

				}
				
			} else {
				
				// no se encuentra el id a actualizar, se regresa error
				resp.setMetadata("Respuesta nok", "-1", "Categoría no encontrada");
				return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.NOT_FOUND);
				
			}
			
		} catch (Exception e) {
			
			resp.setMetadata("Respuesta nok", "-1", "Error al actualizar la consulta");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.OK);
	}

	
	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> delete(Long id) {
		
		CategoryResponseRest resp = new CategoryResponseRest();
		
		try {
			
			categoryDao.deleteById(id);
			resp.setMetadata("Respuesta ok", "00", "Categoría eliminada");
			
		} catch (Exception e) {
			
			resp.setMetadata("Respuesta nok", "-1", "error al eliminar por id");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CategoryResponseRest>(resp, HttpStatus.OK);
		
	}

	
	
}
