package com.formacionbdi.app.productos.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}
	@Override
	@Transactional
	public void deleteById(Long id) {
		productoDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductoMongo> findAllMng() {
		return productoDaoMng.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public ProductoMongo findByIdMng(String id) {
		return productoDaoMng.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public ProductoMongo save(ProductoMongo producto) {
		return productoDaoMng.save(producto);
	}
	@Override
	@Transactional
	public void deleteByIdMng(String id) {
		productoDaoMng.deleteById(id);
	}
	


}
