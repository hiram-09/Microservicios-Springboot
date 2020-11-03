package com.formacionbdi.app.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@PostMapping("/guardar")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto guardar(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PostMapping("/editar")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto) {
		Producto productoDb = productoService.findById(producto.getId());
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		return productoService.save(productoDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}
}
