package br.com.foursys.fourcamp.fourstore.data;

import java.util.ArrayList;

import br.com.foursys.fourcamp.fourstore.interfaces.DataInterfaces;
import br.com.foursys.fourcamp.fourstore.model.Client;

public class ClientData implements DataInterfaces<Client> {
	private static ArrayList<Client> clientList = new ArrayList<Client>();

	@Override
	public void save(Client client) {
		if(!ClientData.clientList.contains(client)) {
			ClientData.clientList.add(client);
		}	
	}

	@Override
	public ArrayList<Client> listAll() {
		if(clientList != null) {
			return clientList;
		}
		return null;
	}

	@Override
	public void update(Client client) {
		for (int x = 0; x < ClientData.clientList.size(); x++) {
			if (client.getCpf() == ClientData.clientList.get(x).getCpf()) {
				ClientData.clientList.set(x, client);
			}
		}
	}
	
	@Override
	public void delete(Client client) {
		ClientData.clientList.remove(client);
	}

	public Client findByCPF(String cpf) {
		Client cliente = null;
		for (int x = 0; x < ClientData.clientList.size(); x++) {
			if (cpf == ClientData.clientList.get(x).getCpf()) {
				cliente = ClientData.clientList.get(x);
				return cliente;
			}
		}
		return cliente;
	}

}