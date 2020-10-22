package com.formacionbdi.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.app.items.clientes.ProductoRestCliente;
import com.formacionbdi.app.items.models.Item;
import com.formacionbdi.app.items.models.Producto;

@Service("ServiceFeign")
public class ServiceItemFeign implements IServiceItem {

	@Autowired
	private ProductoRestCliente cliente;
	
	@Override
	public List<Item> findAll() {
		return cliente.listar().stream().map(p -> new Item(p, 10)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Producto producto = cliente.detalle(id);
		return new Item(producto, cantidad);
	}

}
