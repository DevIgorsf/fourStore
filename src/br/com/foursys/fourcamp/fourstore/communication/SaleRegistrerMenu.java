package br.com.foursys.fourcamp.fourstore.communication;

import java.util.Scanner;

public class SaleRegistrerMenu {
	
	public static void main(String[] args) {
		String sku;
		Integer quantidade;
		String cpf;
		boolean buyisvalid = false;
		String respost;
		String skuestoque = null;
		int quantidadeestoque = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("digite o sku");
		sku = sc.nextLine();
		if(sku.equals(skuestoque)) {
			System.out.println("digite a quantidade:");
			quantidade = sc.nextInt();
			if(quantidade<quantidadeestoque) {
				quantidadeestoque -= quantidade;
				buyisvalid = true;
				
				
			}
			else {
				System.out.println("n�o foi encontrado em estoque s� temos " + quantidadeestoque);
			}
			
		}
		else {
			System.out.println("digite um sku v�lido");
		}
		
		if (buyisvalid) {
			
			System.out.println("Deseja informar o cpf? sim/n�o");
			respost = sc.nextLine();
			if (respost == "sim") {
				
			}
			
			
			
			
			
		}
	}
	

}
