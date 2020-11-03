package com.formacionbdi.app.items.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.app.items.models.Item;
import com.formacionbdi.app.items.models.Producto;
import com.formacionbdi.app.items.models.service.IServiceItem;

@RefreshScope
@RestController
public class ItemController {
	
	@Autowired
	@Qualifier("ServiceTemplate")
	private IServiceItem serviceItem;
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/listar")
	public List<Item> listar(){
		return serviceItem.findAll();
	}
	
	@GetMapping("/detalle/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return serviceItem.findById(id, cantidad);
	}
	
	@GetMapping("/obtenerConfig")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);
		if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return serviceItem.save(producto);
	}
	
	@PostMapping("/editar")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto) {
		return serviceItem.update(producto);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		serviceItem.deleteById(id);
	}
}
