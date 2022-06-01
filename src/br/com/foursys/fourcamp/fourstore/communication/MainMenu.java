package br.com.foursys.fourcamp.fourstore.communication;

import java.util.Scanner;

import br.com.foursys.fourcamp.fourstore.controller.ClientController;
import br.com.foursys.fourcamp.fourstore.controller.MenuController;
import br.com.foursys.fourcamp.fourstore.controller.ProductController;
import br.com.foursys.fourcamp.fourstore.controller.SaleController;
import br.com.foursys.fourcamp.fourstore.enums.PaymentMethod;



public class MainMenu {
	private Scanner scanner;
	private MenuController menuController;
	private SaleController saleController;
	private ClientController clientController;
	private ProductController productController;
	
	public MainMenu() {
		this.menuController = new MenuController();
		this.scanner = new Scanner(System.in);
		this.saleController = new SaleController();
		this.clientController = new ClientController();
		this.productController = new ProductController();
	}

	public void mainMenu() {
		primaryMenu();
	}

	private void primaryMenu() {
		Integer option = -1;
		String entrada;

		while (option != 0) {
			System.out.println("\n\n==========FOURSTORE=============||");
			System.out.println("1 - Produtos                    ||");
			System.out.println("2 - Vendas                      ||");
			System.out.println("0 - Sair do sistema             ||");
			System.out.print("Insira uma opção: ");
			entrada = scanner.next();
			System.out.println("----------------------------------\n");

			option = menuController.validationRegexMenu(entrada, "[0-6]");

			switch (option) {
			case 0:
				System.out.println("\nSistema encerrado");
				break;
			case 1:
				this.menuProducts();
				break;
			case 2:
				this.menuSales();
				break;
			default:
				System.out.println("\nOpcao Invalida. Tente Novamente. \n");
			}
		}
	}

	private void menuSales() {
		int option = -1;
		String entrada;

		while (option != 0) {
			System.out.println("1 - Realizar Venda" + "\n2 - Consultar o histórico de vendas" + "\n3 - Receita líquida de vendas" + "\n0 - Para voltar");
			entrada = scanner.next();

			option = menuController.validationRegexMenu(entrada, "[0-6]");
			switch (option) {
			case 0: {
				primaryMenu();
				break;
			}
			case 1: {
				menuDoSale();
				break;
			}
			case 2: {
				String result = saleController.saleConsultation();
				System.out.println(result);
				break;
			}
			case 3: {
				String result = saleController.netSaleConsultation();
				System.out.println(result);
				break;
			}
			default:
				System.out.println("\nOpcao invalida. Tente Novamente. \n");
			}
		}

	}

	private void menuDoSale() {
		String sku;
		Integer qtt = 0;
		Integer option;
		saleController.clearCart();
		
		while(true) {
			while(true) {
				System.out.println("\nDigite o sku: ");
				sku = scanner.next();
				if (productController.getProductBySku(sku) == "Nao existe um produto com o sku " + sku) {
					System.out.println("Produto nao existe");
				} else if(!(productController.validateSku(sku))) {
					System.out.println("SKU inválido");
				} else {
					break;
				}
			}
			
			while(true) {
				qtt = 0;
				System.out.println("Digite a quantidade:");
				qtt = scanner.nextInt();
				if (qtt < 1) {
					System.out.println("Digite 1 ou mais");
				} else if (!productController.haveStock(sku, qtt)) {
					System.out.println("Quantidade maior do que possuimos" );
					continue;
				} else {
					break;
				}
			}
			ProductController.decrementProduct(sku, qtt);
				
			
			System.out.println(saleController.addCart(sku, qtt)); 
			
			System.out.println("Deseja inserir outro produto?\n 1 - sim\n2 - nao");
			option = scanner.nextInt();
			
			if(option == 1) {
				continue;
			}else if(option == 2) {
				break;
			}else {
				System.out.println("Opcao invalida");
			}	
		}
		

		Integer resposta;
		String cpf = null;
		String nome;

		while (true) {
			System.out.println("deseja colocar o cpf? 1-sim ou 2-nao ?");
			resposta = scanner.nextInt();
			if (resposta == 1) {
				while (true) {
					System.out.println("digite o cpf: ");
					cpf = scanner.next();
					if(!menuController.validateCpfRegex(cpf)) {
						System.out.println("O cpf deve ter o seguinte formato xxx.xxx.xxx-xx");
					} else if (menuController.validarCpf(cpf)) {
						System.out.println("Digite o nome do cliente");
						nome = scanner.next();
						clientController.registerClient(nome, cpf);
						break;
					} else {
						System.out.println("CPF invalido");
					}
				}
				break;
			} else if (resposta == 2) {
				break;
			} else {
				System.out.println("digite uma resposta valida");
			}
		}
		
		
		Integer opcao;
		String dadosCartaoCredito;
		String dadosCartaoDebito;
		String chavePix;
		PaymentMethod paymentmethod;
		
		while (true) {
			System.out.println(
					"Digite a forma de pagamento: \n1- cartao de credito \n2 -cartao de debito \n3- dinheiro \n4-pix");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				paymentmethod = PaymentMethod.CARTAODECREDITO;
				System.out.println("Digite o numero do Cartao");
				dadosCartaoCredito = scanner.next();
				scanner.nextLine();
				if(!menuController.validationCard(dadosCartaoCredito)) {
					System.out.println("Cartao Invalido");
					continue;
				}
				break;
			case 2:
				paymentmethod = PaymentMethod.CARTAODEDEBITO;
				dadosCartaoDebito = scanner.next();
				if(!menuController.validationCard(dadosCartaoDebito)) {
					System.out.println("Cartao Invalido");
					continue;
				}
				break;
			case 3:
				paymentmethod = PaymentMethod.DINHEIRO;
				break;
			case 4:
				paymentmethod = PaymentMethod.PIX;
				System.out.println("Digite e chave pix");
				chavePix = scanner.next();
				if(cpf == null) {
					clientController.registerPix(chavePix);
				} else {
					clientController.registerPix(chavePix, cpf);

				}
				break;
			default:
				System.out.println("opcao invalida");
				continue;
			}
			break;

		}
						
		if(cpf != null) {
			System.out.println(saleController.saleRegister(paymentmethod, cpf)); 
		} else {
			System.out.println(saleController.saleRegister(paymentmethod)); 
		}
		

	}
		
		
	private void menuProducts() {
		int option = -1;
		String entrada;

		while (option != 0) {
			System.out.println("1 - Cadastrar Produto" + "\n2 - Buscar Produto por id" + "\n3 - Buscar Produto por sku"
					+ "\n4 - Lista Produtos" + "\n5 - Atualizar Produto por id" + "\n6 - Atualizar produto por sku"
					+ "\n7 - Excluir Produto pelo id" + "\n8 - Excluir Produto pelo sku" + "\n0 - Para voltar");

			entrada = scanner.next();

			option = menuController.validationRegexMenu(entrada, "[0-8]");

			switch (option) {
			case 0: {
				primaryMenu();
				break;
			}
			case 1: {
				this.cadProduct();
				break;
			}
			case 2: {
				this.getProductById();
				break;
			}
			case 3: {
				this.getProductBySku();
				break;
			}
			case 4: {
				String retorno = productController.listProducts();// metodo para listar produtos
				System.out.println(retorno);
				break;
			}
			case 5: {
				updateProductById();
				break;
			}
			case 6: {
				updateProductBySku();
				break;
			}
			case 7: {
				this.deleteProductById();
				break;
			}
			case 8: {
				this.deleteProductBySku();
				break;
			}
			default:
				System.out.println("\nOpcao Invalida. Tente Novamente \n");
			}
		}
	}
	
	private void updateProductById() {
		String id;
		while(true) {
			System.out.print("\nInsira o id do produto: ");
						
			id = scanner.next();
			String idIsValidMessage = productController.getProductById(id);
			if(idIsValidMessage.equals("Nao existe um produto com o id " + id)) {
				System.out.println(idIsValidMessage + ". Tente novamente.");
				continue;
			}
			break;
		}
		
		this.updateProduct(id, null);
	}
	
	private void updateProductBySku() {
		String sku;
		while(true) {
			System.out.print("\nInsira o sku do produto: ");
			sku = scanner.next();
			String skuIsValidMessage = productController.getProductBySku(sku);
			if(skuIsValidMessage.equals("Nao existe um produto com o sku " + sku)) {
				System.out.println(skuIsValidMessage + ". Tente novamente.");
				continue;
			}
			break;
		}
		
		this.updateProduct(null, sku);
	}
	
	private void updateProduct(String id, String sku) {
		Integer quantity = Integer.MAX_VALUE;
		Double purchasePrice = 0.0, salePrice = 0.0;
		
		String option;
		Boolean condition = true;
		while(condition) {
			System.out.println("\nQual ação deseja realizar?");
			System.out.println("1 - Inserir nova quantidade do produto em estoque");
			System.out.println("2 - Inserir novo preço de compra do produto");
			System.out.println("3 - Inserir novo preço de venda do produto");
			System.out.println("4 - Aplicar atualização dos dados");
			System.out.print("Insira uma opção: ");
			option = scanner.next();
			
			switch(option) {
				case "1":
					quantity = this.getNewQuantityProduct();
					break;
				case "2":
					purchasePrice = this.getNewPurchasePrice();
					break;
				case "3":
					salePrice = this.getNewSalePrice();
					break;
				case "4":
					if(id != null) {
						System.out.println(productController.updateProductById(id, quantity, purchasePrice, salePrice));
						condition = false;
					} else if(sku != null) {
						System.out.println(productController.updateProductBySku(sku, quantity, purchasePrice, salePrice));
						condition = false;
					} else {
						System.out.println("Não foi possível realizar a atualização dos dados. Tente novamente.");
					}
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
					continue;
			}
		}
	}
	
	private Integer getNewQuantityProduct() {
		Integer quantity;
		while(true) {
			System.out.print("\nInsira a nova quantidade em estoque do produto: ");
			quantity = scanner.nextInt();
			if(quantity < 0) {
				System.out.println("Valor inválido. Tente novamente.");
				continue;
			}
			break;
		}
		return quantity;
	}
	
	private Double getNewPurchasePrice() {
		Double purchasePrice;
		while(true) {
			System.out.print("\nInsira o novo preço de compra do produto: ");
			purchasePrice = scanner.nextDouble();
			if(purchasePrice < 0.0) {
				System.out.println("Valor inválido. Tente novamente.");
				continue;
			}
			break;
		}
		return purchasePrice;
	}
	
	private Double getNewSalePrice() {
		Double salePrice;
		while(true) {
			System.out.print("\nInsira o novo preço de venda do produto: ");
			salePrice = scanner.nextDouble();
			if(salePrice < 0.0) {
				System.out.println("Valor inválido. Tente novamente.");
				continue;
			}
			break;
		}
		return salePrice;
	}
	
	public void cadProduct() {
		String sku;
		
		while (true) {
			System.out.println("Insira o sku do produto");
			sku = scanner.next();
			if(productController.productIsRegistered(sku)) {
				System.out.println("SKU já cadastrado. \n");
			} else if(!(productController.validateSku(sku))) {
				System.out.println("SKU inválido");
			} else {
				break;
			}
		}
		
		System.out.println("Insira a descricao do produto");
		scanner.nextLine();
		String description = scanner.nextLine();
		
		System.out.println("Insira a quantidade do produto");
		Integer quantity = scanner.nextInt();
		
		System.out.println("Insira o valor de compra do produto \nR$");
		Double purchasePrice = scanner.nextDouble();
		
		System.out.println("Insira o valor de venda do produto \nR$");
		Double salePrice = scanner.nextDouble();
		
		String retorno = productController.cadProduct(sku, description, quantity, purchasePrice, salePrice);
		System.out.println(retorno);
	}
	
	
	private void getProductById() {
		System.out.print("\nInsira o id do produto: ");
		String id = scanner.next();
		System.out.println(productController.getProductById(id) + "\n");
	}
	
	private void getProductBySku() {
		System.out.print("\nInsira o sku do produto: ");
		String sku = scanner.next();
		if(!(productController.validateSku(sku))) {
			System.out.println("SKU inválido");
		} else {
			System.out.println(productController.getProductBySku(sku) + "\n");
		}
		
	}
	
	private void deleteProductById() {
		System.out.print("\nInsira o id do produto: ");
		String id = scanner.next();
		System.out.println(productController.deleteProductById(id) + "\n");
	}
	
	private void deleteProductBySku() {
		System.out.print("\nInsira o sku do produto: ");
		String sku = scanner.next();
		if(!(productController.validateSku(sku))) {
			System.out.println("SKU inválido");
		}else {
			System.out.println(productController.deleteProductBySku(sku) + "\n");
		}
	}
}

