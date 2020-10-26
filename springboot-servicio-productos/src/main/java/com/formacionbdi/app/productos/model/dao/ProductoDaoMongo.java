package com.formacionbdi.app.productos.model.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formacionbdi.app.productos.model.entity.ProductoMongo;

public interface ProductoDaoMongo extends MongoRepository<ProductoMongo, String>{
}
