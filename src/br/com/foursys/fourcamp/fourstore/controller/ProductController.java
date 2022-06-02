package br.com.foursys.fourcamp.fourstore.controller;

import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.service.ProductService;
import br.com.foursys.fourcamp.fourstore.utils.Validations;

public class ProductController {
	private static ProductService productService = new ProductService();
	private static Validations validations = new Validations();
	
	public String cadProduct(String sku, String description, 
			Integer quantity, Double purchasePrice, Double salePrice) {
		
		String retorno = "";
		
		if(quantity < 0) {
			return retorno = "Valor de quantidade não pode ser negativo";
		} else if(purchasePrice < 0) {
			return retorno = "Valor de quantidade não pode ser negativo";
		} else if(salePrice < 0) {
			return retorno = "Valor de quantidade não pode ser negativo";
		}
		
		Product product = new Product(sku, description, quantity, purchasePrice, salePrice);
		
		if (product == null) {
			return retorno = "nao foi possivel cadastrar o produto";
		}
		
		retorno = "\nO produto foi cadastrado com sucesso!\n"
				+ "\n SKU: " + product.getSku()
				+ "\n ID: " + product.getId()
				+ "\n Descricao: " + product.getDescription()
				+ "\n Tipo: " + product.getType()
				+ "\n Tamanho: " + product.getSize()
				+ "\n Cor: " + product.getColor()
				+ "\n Categoria: " + product.getCategory()
				+ "\n Estacao: " + product.getSeason()
				+ "\n Quantidade: " + product.getQuantity()
				+ "\n Preco de Compra: " + product.getPurchasePrice()
				+ "\n Preco de Venda: " + product.getSalePrice() + "\n";
		productService.cadProduct(product);
		
		return retorno;
	}
	
	public Boolean productIsRegistered(String sku) {
		if(productService.productIsRegistered(sku)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getProductById(String id) {
		Product product = productService.getById(id);
		if(product == null) return "Nao existe um produto com o id " + id;
		return product.toString();
	}
	
	public String getProductBySku(String sku) {
		Product product = productService.getBySku(sku);
		if(product == null) return "Nao existe um produto com o sku " + sku;
		return product.toString();
	}
	
	public String deleteProductById(String id) {
		if(productService.deleteProductById(id)) return "Produto excluido!";
		return "Nao existe um produto com o id " + id;
	}
	
	public String deleteProductBySku(String sku) {
		if(productService.deleteProductBySku(sku)) return "Produto excluido pelo sku!";
		return "Nao existe um produto com o sku " + sku;
	}
	
	public String updateProductBySku(String Sku, Integer quantity, Double purchasePrice, Double salePrice) {
		Product product = new Product(Sku, quantity, purchasePrice, salePrice);
		return (productService.updateBySku(product)) ? "Produto alterado com sucesso!" : "Dados invalidos! O produto nao foi alterado.";
	}
	
	public String updateProductById(String id, Integer quantity, Double purchasePrice, Double salePrice) {
		Product oldProduct = productService.getById(id);
		String sku = oldProduct.getSku();
		Product product = new Product(sku, quantity, purchasePrice, salePrice);
		return (productService.updateBySku(product)) ? "Produto alterado com sucesso!" : "Dados invalidos! O produto nao foi alterado.";
	}
	
	public String listProducts() {
		return productService.listProductService();
	}
	
	public Boolean haveStock(String sku, Integer quantity) {
		return productService.haveStock(sku, quantity);
	}
	
	public Product getProductBySkuObject (String sku) {
		return productService.getProductBySku(sku);
	}

	public static void decrementProduct(String sku, Integer qtt) {
		productService.decrementProduct(sku, qtt);
		}
	

	public boolean validateSku(String sku) {
		if (!validations.validateSkuRegex(sku)) {
			return false;
		}
		
		int category = Integer.parseInt(sku.substring(2, 4));
		int color = Integer.parseInt(sku.substring(4, 6));
		int season = Integer.parseInt(sku.substring(6, 10));
		int type = Integer.parseInt(sku.substring(10, 12));
		int size = Integer.parseInt(sku.substring(12, 14));
		
		if(category > 40 || category < 31) {
			return false;
		} else if (color > 10 || color < 1) {
			return false;
		} else if(!(season == 9012 || season == 3030 || season == 3060 || season == 6090)) {
			return false;
		} else if(type > 96 || type < 92) {
			return false;
		} else if(size > 54 || size < 50) {
			return false;
		}
		
		return true;
	}
}

