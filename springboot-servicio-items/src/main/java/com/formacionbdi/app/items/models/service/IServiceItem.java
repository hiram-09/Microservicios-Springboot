package com.formacionbdi.app.items.models.service;

import java.util.List;

import com.formacionbdi.app.items.models.Item;
import com.formacionbdi.app.items.models.ItemMongo;

public interface IServiceItem {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
	
	public List<ItemMongo> findAllMng();
	
	public ItemMongo findById(String id, Integer cantidad);
}
