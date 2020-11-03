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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.app.productos.model.entity.ProductoMongo;
import com.formacionbdi.app.productos.model.service.IProductoService;

@RestController
@RequestMapping("/mng")
public class ProductosControllerMng {
	
	@Autowired
	private IProductoService productoService;
	
	@Value("${server.port}")
	private Integer port;
	
	@GetMapping("/listar")
	public List<ProductoMongo> listar(){
		List<ProductoMongo> productos = productoService.findAllMng();
		return productos.stream().map(p->{
			p.setPort(port);
			return p;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/detalle/{id}")
	public ProductoMongo detalle(@PathVariable String id) {
		ProductoMongo producto = productoService.findByIdMng(id);
		producto.setPort(port);
		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductoMongo crear(@RequestBody ProductoMongo producto) {
		return productoService.save(producto);
	}
	
	@PostMapping("/editar")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductoMongo editar(@RequestBody ProductoMongo producto) {
		ProductoMongo productoDb = productoService.findByIdMng(producto.getId());
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		return productoService.save(productoDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable String id) {
		productoService.deleteByIdMng(id);
	}
}
