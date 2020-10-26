package com.formacionbdi.app.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.app.items.models.Item;
import com.formacionbdi.app.items.models.ItemMongo;
import com.formacionbdi.app.items.models.Producto;
import com.formacionbdi.app.items.models.ProductoMongo;

@Service("ServiceTemplate")
public class ServiceItemImpl implements IServiceItem {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(restTemplate.getForObject("http://localhost:8080/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p, 10)).collect(Collectors.toList());
	}
	
	@Override
	public Item findById(Long id, Integer cantidad) {
		HashMap<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		Producto producto = restTemplate.getForObject("http://localhost:8080/detalle/{id}", Producto.class, params);
		return new Item(producto, cantidad);
	}
	
	@Override
	public List<ItemMongo> findAllMng() {
		List<ProductoMongo> productos = Arrays.asList(restTemplate.getForObject("http://localhost:8080/listar", ProductoMongo[].class));
		return productos.stream().map(p -> new ItemMongo(p, 10)).collect(Collectors.toList());
	}
	
	@Override
	public ItemMongo findById(String id, Integer cantidad) {
		HashMap<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		ProductoMongo producto = restTemplate.getForObject("http://localhost:8080/detalle/{id}", ProductoMongo.class, params);
		return new ItemMongo(producto, cantidad);
	}
}
