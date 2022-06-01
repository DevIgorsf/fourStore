package br.com.foursys.fourcamp.fourstore.data;

import java.util.ArrayList;

import br.com.foursys.fourcamp.fourstore.model.Sale;

public class SaleData {
	private static ArrayList<Sale> saleList = new ArrayList<Sale>();
	private static Double netSales = 0.00;
	
	public void save(Sale sale) {
		saleList.add(sale);
	}
	
	public ArrayList<Sale> list() {
			return saleList;
	}
	
	public Double getNetSales() {
		return netSales;
	}
	
	public void setNetSales(Double netSales) {
		SaleData.netSales += netSales;
	}
}
