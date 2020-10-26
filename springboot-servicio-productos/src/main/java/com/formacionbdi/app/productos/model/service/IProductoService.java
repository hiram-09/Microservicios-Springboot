package com.formacionbdi.app.productos.model.service;

import java.util.List;

import com.formacionbdi.app.productos.model.entity.Producto;
import com.formacionbdi.app.productos.model.entity.ProductoMongo;

public interface IProductoService {
	
	//Metodos para la BD de MySQL
	public List<Producto> findAll();
	
	public Producto findById(Long id);
	
	
	//Metodos para MongoDB
	public List<ProductoMongo> findAllMng();
	
	public ProductoMongo findByIdMng(String id);
}
