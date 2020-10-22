package com.formacionbdi.app.items.models.service;

import java.util.List;

import com.formacionbdi.app.items.models.Item;

public interface IServiceItem {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
}
