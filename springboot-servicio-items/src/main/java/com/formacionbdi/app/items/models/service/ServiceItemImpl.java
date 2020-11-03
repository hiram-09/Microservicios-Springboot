package com.formacionbdi.app.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
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
		System.out.println("RestTemplate");
		List<Producto> productos = Arrays.asList(restTemplate.getForObject("http://servicio-productos/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p, 10)).collect(Collectors.toList());
	}
	
	@Override
	public Item findById(Long id, Integer cantidad) {
		HashMap<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		Producto producto = restTemplate.getForObject("http://servicio-productos/detalle/{id}", Producto.class, params);
		return new Item(producto, cantidad);
	}
	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);
		ResponseEntity<Producto> productoNew = restTemplate.exchange("http://servicio-productos/guardar", HttpMethod.POST, body, Producto.class);
		return productoNew.getBody();
	}
	@Override
	public Producto update(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);
		ResponseEntity<Producto> productoNew = restTemplate.exchange("http://servicio-productos/editar", HttpMethod.POST, body, Producto.class);
		return productoNew.getBody();
	}
	@Override
	public void deleteById(Long id) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id.toString());
		restTemplate.delete("http://servicio-productos/eliminar/{id}", params);		
	}

	@Override
	public List<ItemMongo> findAllMng() {
		List<ProductoMongo> productos = Arrays.asList(restTemplate.getForObject("http://servicio-productos/mng/listar", ProductoMongo[].class));
		return productos.stream().map(p -> new ItemMongo(p, 10)).collect(Collectors.toList());
	}
	
	@Override
	public ItemMongo findById(String id, Integer cantidad) {
		HashMap<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		ProductoMongo producto = restTemplate.getForObject("http://servicio-productos/mng/detalle/{id}", ProductoMongo.class, params);
		return new ItemMongo(producto, cantidad);
	}

}
