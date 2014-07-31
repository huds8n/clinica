package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import model.Medico;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;

import dao.HibernateDAO;


@ManagedBean(name = "medicoBean")
@ViewScoped
public class MedicoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Medico registro;
	private Medico MedicoSelecionado;
	private List<Medico> listagem;
	private String uf;
	private String filtro;
	private int inicio;
	private int fim;

	public MedicoBean() {
		registro = new Medico();
		MedicoSelecionado = new Medico();
		listagem = null;
		filtro = new String();
		uf = "DF";
		inicio = 0;
		fim = 0;
	}

	//METODOS DE NAVEGAÇÃO...
	public String editar() {
		registro = MedicoSelecionado;
		return "alterarMedico.xhtml?faces-redirect=true";
	}


	public List<String> completaNome(String query) {
		List<String> estados = new ArrayList<String>();
		List<String> sugestoes = new ArrayList<String>();
		for (String j : estados) {
			if (j.startsWith(query.toUpperCase())) {
				sugestoes.add(j);
			}
		}
		return sugestoes;
	}


	public String incluir() {
		try {
			String crm = registro.getCrm()+"/"+uf.toUpperCase();
			registro.setCrm(crm);
			HibernateDAO<Medico, Integer> regHBR = new HibernateDAO<Medico, Integer>(Medico.class);

			if (regHBR.inclui(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Obrigado.","Registro incluido com sucesso.Confira Abaixo!!!"));
				return "/paginas/index.xhtml";
			}
		} catch (ConstraintViolationException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Chave primária já existe. Registro duplicado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na inclusão dos dados."));
			e.printStackTrace();
		}
		return "incluirMedico.xhtml";

	}

	public String alterar() {
		try {
			HibernateDAO<Medico, Integer> regHBR = new HibernateDAO<Medico, Integer>(Medico.class);
			if (regHBR.altera(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!","Registro alterado com sucesso.Confira abaixo"));
				return "/paginas/index.xhtml";
			}
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Objeto não localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na exclusão dos dados."));
			e.printStackTrace();
		}
		return "/paginas/index.xhtml";

	}
	public void consultar(ActionEvent event) {
		try {
			HibernateDAO<Medico, Integer> regHBR = new HibernateDAO<Medico, Integer>(Medico.class);
			registro = (Medico) regHBR.consulta(registro.getIdmedico());
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Objeto não localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha de consulta aos dados."));
			e.printStackTrace();
		}

	}

	public void listarTudo() {
		try {
			HibernateDAO<Medico, Integer> regHBR = new HibernateDAO<Medico, Integer>(Medico.class);
			listagem = regHBR.listaTudo();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public String deletar(Medico up) {
		try {
			registro = up;
			System.out.println("Id Up" + registro.getIdmedico());
			HibernateDAO<Medico, Integer> regHBR2 = new HibernateDAO<Medico, Integer>(Medico.class);
			registro = (Medico) regHBR2.consulta(registro.getIdmedico());
			HibernateDAO<Medico, Integer> regHBR = new HibernateDAO<Medico, Integer>(Medico.class);
			if (regHBR.exclui(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "INFO!",
						"Registro excluído com sucesso. Confira Abaixo!!!"));
				return "listarUp.xhtml";
			}
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Objeto não localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na exclusão dos dados."));
			e.printStackTrace();
		}
		return "/paginas/up/consultar.xhtml";
	}

	public void listarTudo(ActionEvent event) {
		try {
			HibernateDAO<Medico, Integer> regHBR = new HibernateDAO<Medico, Integer>(Medico.class);
			listagem = regHBR.listaTudo();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}



	public void listarPagina(ActionEvent event) {
		try {
			HibernateDAO<Medico, Integer> regHBR = new HibernateDAO<Medico, Integer>(Medico.class);
			listagem = regHBR.pagina(inicio, fim);
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void listarLike(ActionEvent event) {
		try {
			HibernateDAO<Medico, Integer> regHBR = new HibernateDAO<Medico, Integer>(Medico.class);
			listagem = regHBR.listaLike("nome", filtro+"%");
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public Medico getRegistro() {
		return registro;
	}

	public void setRegistro(Medico registro) {
		this.registro = registro;
	}

	public List<Medico> getListagem() {
		return listagem;
	}

	public void setListagem(List<Medico> listagem) {
		this.listagem = listagem;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFim() {
		return fim;
	}

	public void setFim(int fim) {
		this.fim = fim;
	}

	public Medico getMedicoSelecionado() {
		return MedicoSelecionado;
	}

	public void setMedicoSelecionado(Medico MedicoSelecionado) {
		this.MedicoSelecionado = MedicoSelecionado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}