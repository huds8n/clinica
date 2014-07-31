package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "autoCompleteBean")
@RequestScoped
public class AutoCompleteBean {
	List<String> clientes;

	public AutoCompleteBean() {
		clientes = new ArrayList<String>();
		popularClientes();
	}

	private void popularClientes(){
		float num = 0.00f;
		for (int i=0; i < 100; i++){
			num+=0.25f;
			System.out.println(num);
			clientes.add(Float.toString(num));
		}
	}
	private String cliente;

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<String> complete(String busca) {
		List<String> results = new ArrayList<String>();

		for (String cliente : clientes) {
			if (cliente.toUpperCase().contains(busca.toUpperCase()))
				results.add(cliente);
		}
		return results;

	}

}
