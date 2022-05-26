package br.com.foursys.fourcamp.fourstore.service;

import java.util.ArrayList;

import br.com.foursys.fourcamp.fourstore.data.ProductData;
import br.com.foursys.fourcamp.fourstore.model.Product;

public class ProductService {

	private ProductData productData = new ProductData();
	
	public Product getById(String id) {
		return this.productData.getProductById(id);
	}
	
	public Product getBySku(String sku) {
		return this.productData.getProductBySku(sku);
	}
	
	public Boolean updateBySku(Product updatedProduct) {
		String Sku = updatedProduct.getSku();

		Product originalProduct = this.productData.getProductBySku(Sku);
		if(originalProduct == null) {
			return false;
		}

		return update(updatedProduct);
	}
	
	private Boolean update(Product updatedProduct) { 
		Integer updatedQuantity = updatedProduct.getQuantity();
		if(updatedQuantity == null || updatedQuantity < 0) {
			return false;
		}
		
		Double updatedPurchasePrice = updatedProduct.getPurchasePrice();
		if(updatedPurchasePrice == null || updatedPurchasePrice < 0) {
			return false;
		}
		
		Double updatedSalePrice = updatedProduct.getSalePrice();
		if(updatedSalePrice == null || updatedSalePrice < 0) {
			return false;
		}
		
		this.productData.updateProduct(updatedProduct);
		return true;
	}
	
	public Boolean deleteProductById(String id) {
		Product product = this.productData.getProductById(id);
		return this.deleteProduct(product);
	}
	
	public Boolean deleteProductBySky(String sku) {
		Product product = this.productData.getProductBySku(sku);
		return this.deleteProduct(product);
	}
	
	private Boolean deleteProduct(Product product) {
		if(product == null) {
			return false;	
		}
		this.productData.deleteProduct(product);
		return true;
	}
	
	public String listProductService() {
		String retorno = "";
		ArrayList<Product> lista = new ArrayList<Product>();
		lista = productData.getAllProducts();
		for (Product list : lista) {
			retorno += list.toString();
		}
		return retorno;
	}
	
	public Boolean haveStock(String sku, Integer quantity) {
		Product productInStock = productData.getProductBySku(sku);
		if(productInStock != null) {
			Integer quantityInStock = productInStock.getQuantity();
			if(quantityInStock >= quantity) {
				return true;
			}
		}

		return false;
	}
	
	public Product getProductBySkuObject(String sku) {
		return data.getProductBySkuObject(sku);
	}
	
}
