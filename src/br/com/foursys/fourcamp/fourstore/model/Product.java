package br.com.foursys.fourcamp.fourstore.model;

import br.com.foursys.fourcamp.fourstore.enums.CategoryEnum;
import br.com.foursys.fourcamp.fourstore.enums.ColorEnum;
import br.com.foursys.fourcamp.fourstore.enums.SeasonEnum;
import br.com.foursys.fourcamp.fourstore.enums.SizeEnum;
import br.com.foursys.fourcamp.fourstore.enums.TypeEnum;

public class Product {
	
	private String sku;
	private String id;
	private String description;
	private TypeEnum type;
	private SizeEnum size;
	private ColorEnum color;
	private CategoryEnum category;
	private SeasonEnum season;
	private Integer quantity;
	private Double purchasePrice;
	private Double salePrice;

	public Product(String sku, Integer quantity, Double purchasePrice, Double salePrice) {
		this.sku = sku;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		parseSku(sku);
	}

	public Product(String sku, String description, Integer quantity, Double purchasePrice, Double salePrice) {
		this.sku = sku;
		this.description = description;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		parseSku(sku);
	}
	
	public Product(String sku, String description, TypeEnum type, SizeEnum size, ColorEnum color, CategoryEnum category, SeasonEnum season, Integer quantity, Double purchasePrice, Double salePrice) {
		this.sku = sku;
		//this.id = id;
		this.description = description;
		this.type = type;
		this.size = size;
		this.color = color;
		this.category = category;
		this.season = season;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		parseSku(sku);
	}	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public SizeEnum getSize() {
		return size;
	}

	public void setSize(SizeEnum size) {
		this.size = size;
	}

	public ColorEnum getColor() {
		return color;
	}

	public void setColor(ColorEnum color) {
		this.color = color;
	}

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

	public SeasonEnum getSeason() {
		return season;
	}

	public void setSeason(SeasonEnum season) {
		this.season = season;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
	public void update(Product product) {
		this.quantity = product.getQuantity();
		this.salePrice = product.getSalePrice();
		this.purchasePrice = product.getPurchasePrice();
	}

	private void parseSku(String sku) {
		this.id = sku.substring(0, 2);
		this.category = CategoryEnum.get(sku.substring(2, 4));
		this.color = ColorEnum.get(sku.substring(4, 6));
		this.season = SeasonEnum.get(sku.substring(6, 10));
		this.type = TypeEnum.get(sku.substring(10, 12));
		this.size = SizeEnum.get(sku.substring(12, 14));
	}

	@Override
	public String toString() {
		return "\n\nsku: " + sku + "\nid: " + id + "\nDescricao: " + ((this.description == null) ? "" : this.description)
				+ "\nTipo: " + type + "\nTamanho: " + size + "\nCor: " + color 
				+ "\nCategoria: " + category + "\nEstação: " + season + "\nQuantidade: " + quantity
				+ "\nPreço de compra: R$" + purchasePrice + "\nPreço de venda: R$" + salePrice + "\n\n";
	}


	public void decrementProduct(Integer quantity) {
		this.quantity -= quantity;
	}
	
}
