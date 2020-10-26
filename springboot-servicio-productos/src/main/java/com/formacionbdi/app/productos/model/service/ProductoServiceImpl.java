package com.formacionbdi.app.productos.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.app.productos.model.dao.ProductoDao;
import com.formacionbdi.app.productos.model.dao.ProductoDaoMongo;
import com.formacionbdi.app.productos.model.entity.Producto;
import com.formacionbdi.app.productos.model.entity.ProductoMongo;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private ProductoDaoMongo productoDaoMng;
	
	@Autowired
	private ProductoDao productoDao;
	
	@Override
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}
	@Override
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}
	@Override
	public List<ProductoMongo> findAllMng() {
		return productoDaoMng.findAll();
	}
	@Override
	public ProductoMongo findByIdMng(String id) {
		return productoDaoMng.findById(id).orElse(null);
	}


}
