package com.formacionbdi.app.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.app.productos.model.entity.Producto;
import com.formacionbdi.app.productos.model.service.IProductoService;

@RestController
public class ProductosController {
	
	@Autowired
	private IProductoService productoService;
	
	@Value("${server.port}")
	private Integer port;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		List<Producto> productos = productoService.findAll();
		return productos.stream().map(p ->{
			p.setPort(port);
			return p;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/detalle/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		producto.setPort(port);
		return producto;
	}
}
