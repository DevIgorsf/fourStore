package br.com.foursys.fourcamp.fourstore.controller;

import br.com.foursys.fourcamp.fourstore.model.Sale;
import br.com.foursys.fourcamp.fourstore.service.SaleService;

public class SaleController {
	
	SaleService saleService = new SaleService();
	
	public String saleRegister(Sale sale) {
		String retorno = "";
		if(saleService.saveSale(sale)) {
			retorno = "Venda registrada com sucesso!";
			return retorno;
		} else {
			retorno = "Est� venda n�o pode ser registrada";
			return retorno;
		}
	}
	
	public String saleConsultation() {
		String retorno = "";
		if(saleService.listSale() == null) {
			retorno = "N�o h� nunhum hist�rico de vendas!";
			return retorno;
		}
		retorno = saleService.listSale().toString();
		return retorno;
	}

}
