package com.formacionbdi.app.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.app.productos.model.entity.ProductoMongo;
import com.formacionbdi.app.productos.model.service.IProductoService;

@RestController
@RequestMapping("/mng")
public class ProductosControllerMng {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<ProductoMongo> listar(){
		return productoService.findAllMng();
	}
	
	@GetMapping("/detalle/{id}")
	public ProductoMongo detalle(@PathVariable String id) {
		return productoService.findByIdMng(id);
	}
}
