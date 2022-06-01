package br.com.foursys.fourcamp.fourstore.service;

import java.util.ArrayList;
import java.util.List;

import br.com.foursys.fourcamp.fourstore.controller.ProductController;
import br.com.foursys.fourcamp.fourstore.data.SaleData;
import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.model.Sale;

public class SaleService {
	
	private static ArrayList<Product> cart = new ArrayList<Product>();
	private static ProductController productController = new ProductController();
	private static SaleData saleData = new SaleData();

	public void saveSale(Sale sale) {
		
		saleData.save(sale);
	}
	
	public List<Sale> listSale() {
		return saleData.list();
	}
	
	public Double amountValue(List<Product> products) {
		Double amountValue = 0.0;
		Double netSales = 0.0;
		for(int i = 0; i < products.size(); i++) {
			amountValue += products.get(i).getSalePrice() * products.get(i).getQuantity();
			netSales += (products.get(i).getSalePrice() * products.get(i).getQuantity()) - ( products.get(i).getPurchasePrice() * products.get(i).getQuantity());
		}
		saleData.setNetSales(netSales);
		return amountValue;
	}
	
	
	public boolean addCart(String sku, Integer quantity) {
		Product currentProduct = productController.getProductBySkuObject(sku);
		Product productGeneric = new Product(sku, quantity, currentProduct.getPurchasePrice(), currentProduct.getSalePrice());

		cart.add(productGeneric);
		return true;
		}
	
	public void clearCart() {
		cart.clear();
	}
	
	public List<Product> cart() {
		return cart;
	}

	public static ArrayList<Product> getCart() {
		return cart;
	}

	public String netSaleConsultation() {
		Double netSale = saleData.getNetSales();
		if(netSale == null) {
			return "Não há vendas cadastradas";
		}
		return "R$" + netSale;
	}
	
}
