package com.formacionbdi.app.items.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.app.items.models.ItemMongo;
import com.formacionbdi.app.items.models.service.IServiceItem;

@RestController
@RequestMapping("/mng")
public class ItemControllerMongo {
	
	@Autowired
	@Qualifier("ServiceFeign")
	private IServiceItem serviceItem;
	
	@GetMapping("/listar")
	public List<ItemMongo> listarMng(){
		return serviceItem.findAllMng();
	}
	
	@GetMapping("/detalle/{id}/cantidad/{cantidad}")
	public ItemMongo detalleMng(@PathVariable String id, @PathVariable Integer cantidad) {
		return serviceItem.findById(id, cantidad);
	}
}
