package com.formacionbdi.app.items.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.app.items.models.Producto;
import com.formacionbdi.app.items.models.ProductoMongo;

@FeignClient(name = "servicio-productos")
public interface ProductoRestCliente {
	
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/detalle/{id}")
	public Producto detalle(@PathVariable Long id);
	
	@GetMapping("/mng/listar")
	public List<ProductoMongo> listarMng();
	
	@GetMapping("/mng/detalle/{id}")
	public ProductoMongo detalleMng(@PathVariable String id);
}
