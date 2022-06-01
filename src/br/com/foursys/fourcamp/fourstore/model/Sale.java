package br.com.foursys.fourcamp.fourstore.model;

import br.com.foursys.fourcamp.fourstore.enums.PaymentMethod;

public class Sale {
	
	private Client client;
	private String products;
	private Double amountValue;
	private PaymentMethod paymentMethod;
	
	public Sale(String string, Double amountValue, PaymentMethod paymentMethod) {
		this.products = string;
		this.amountValue = amountValue;
		this.paymentMethod = paymentMethod;
	}
	
	public Sale(Client client, String products, Double amountValue, PaymentMethod paymentMethod) {
		this.client = client;
		this.products = products;
		this.amountValue = amountValue;
		this.paymentMethod = paymentMethod;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public Double getAmountValue() {
		return amountValue;
	}

	public void setAmountValue(Double amountValue) {
		this.amountValue = amountValue;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		if(this.client != null) {
			return "\n\nCliente: " +  client.getName() 
			 + "\n\nChave pix: " +  client.getPixKey() 
		     + "\nProdutos: " + products
		     + "\nValor total: R$" + amountValue
		     + "\nMétodo de pagamento: " + paymentMethod.getDescription() + "\n";
		} else {
			return "\nVenda para o cliente: cliente não informado"
			 + "\nProdutos: " + products
		     + "\nValor total: R$" + amountValue
		     + "\nMétodo de pagamento: " + paymentMethod.getDescription() + "\n";
		}
		
	}
	

	
} 
