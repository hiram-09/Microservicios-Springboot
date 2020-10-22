package com.formacionbdi.app.items.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.app.items.models.Producto;

@FeignClient(name = "servicio-productos", url="http://localhost:8080")
public interface ProductoRestCliente {
	
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/detalle/{id}")
	public Producto detalle(@PathVariable Long id);
}