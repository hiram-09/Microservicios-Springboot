package com.formacionbdi.app.items.models;

public class ItemMongo {

	private ProductoMongo producto;
	private Integer cantidad;
	
	
	public ItemMongo(ProductoMongo producto, Integer cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public ProductoMongo getProducto() {
		return producto;
	}
	public void setProducto(ProductoMongo producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getTotal() {
		return this.producto.getPrecio()*this.cantidad;
	}
	
}
