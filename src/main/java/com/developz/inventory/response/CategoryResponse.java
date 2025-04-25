package com.developz.inventory.response;

import java.util.List;

import com.developz.inventory.model.Category;

import lombok.Data;

@Data

public class CategoryResponse {

	private List<Category> category;

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	
	
}
