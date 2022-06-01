package br.com.foursys.fourcamp.fourstore;

import br.com.foursys.fourcamp.fourstore.communication.MainMenu;
import br.com.foursys.fourcamp.fourstore.data.ProductData;
import br.com.foursys.fourcamp.fourstore.model.Product;

public class Application {

	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		
		menu.mainMenu();
	}
}
