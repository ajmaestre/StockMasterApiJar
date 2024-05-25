package main.java.com.engineerds.stockmaster.model;

import java.util.ArrayList;

public class Facturas {

	private Factura factura;
	private ArrayList<Detalle> detalles;
	
	public Facturas(){
		
	}
	
	public Facturas(Factura factura, ArrayList<Detalle> detalles){
		this.factura = factura;
		this.detalles = detalles;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public ArrayList<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<Detalle> detalles) {
		this.detalles = detalles;
	}
	
}
