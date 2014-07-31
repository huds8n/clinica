package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import model.Cliente;
import model.Medico;
import model.Refracao;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;

import dao.HibernateDAO;

@ManagedBean(name = "refraBean")
@ViewScoped
public class RefracaoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Refracao registro;
	private Refracao RefracaoSelecionada;
	private List<Refracao> listagem;
	private Cliente cliente;
	private String filtro;
	private Date dataFiltro;
	private int inicio;
	private int fim;
	List<String> strMedicos;
	private List<Medico> medicos = new ArrayList<Medico>();
	private List<String> nomes = new ArrayList<String>();

	public RefracaoBean() {
		strMedicos = new ArrayList<String>();
		this.RefracaoSelecionada = new Refracao();
		this.registro = new Refracao();
		cliente = new Cliente();

		this.listagem = null;
		this.filtro = new String();
		this.inicio = 0;
		this.fim = 0;
		listarMedicos();

	}

	public List<String> complete(String busca) {
		List<String> results = new ArrayList<String>();

		for (String nomeMedico : strMedicos) {
			if (nomeMedico.toUpperCase().contains(busca.toUpperCase()))
				results.add(nomeMedico);
		}
		return results;

	}

	public void listarMedicos() {
		HibernateDAO<Medico, Integer> regHBR = new HibernateDAO<Medico, Integer>(
				Medico.class);
		medicos = regHBR.listaAll();
		Collections.sort(medicos);

	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public String incluir() {
		try {
			HibernateDAO<Refracao, Integer> regHBR = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
			registro.setDta(new java.util.Date());
			registro.setData(new java.util.Date());
			registro.setCliente(cliente);
			if (regHBR.inclui(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "INFO!",
						"Registro incluido com sucesso."));
				return "iniciarConsulta2.xhtml";
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

		return "iniciarConsulta2.xhtml";
	}

	public void excluir(ActionEvent event) {
		try {
			HibernateDAO<Refracao, Integer> regHBR2 = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
			registro = (Refracao) regHBR2.consulta(registro.getIdRefracao());
			HibernateDAO<Refracao, Integer> regHBR = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
			if (regHBR.exclui(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "INFO!",
						"Registro excluído com sucesso."));
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

	}

	public String alterar() {
		try {
			HibernateDAO<Refracao, Integer> regHBR = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
			if (regHBR.altera(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "INFO!",
						"Registro alterado com sucesso."));
				return "listarRefracao.xhtml";
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
			HibernateDAO<Refracao, Integer> regHBR = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
			registro = (Refracao) regHBR.consulta(registro.getIdRefracao());
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

	public void listarTudo(ActionEvent event) {
		try {
			HibernateDAO<Refracao, Integer> regHBR = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
			listagem = regHBR.listaTudo();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void listarTudo() {
		try {
			HibernateDAO<Refracao, Integer> regHBR = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
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
			HibernateDAO<Refracao, Integer> regHBR = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
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
			HibernateDAO<Refracao, Integer> regHBR = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
			listagem = regHBR.listaLike("nome", filtro + "%");
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public Refracao getRegistro() {
		return registro;
	}

	public void setRegistro(Refracao registro) {
		this.registro = registro;
	}

	public List<Refracao> getListagem() {
		return listagem;
	}

	public void setListagem(List<Refracao> listagem) {
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

	public Refracao getRefracaoSelecionada() {
		return RefracaoSelecionada;
	}

	public void setRefracaoSelecionada(Refracao RefracaoSelecionada) {
		this.RefracaoSelecionada = RefracaoSelecionada;
	}

	public Date getDataFiltro() {
		return dataFiltro;
	}

	public void setDataFiltro(Date dataFiltro) {
		this.dataFiltro = dataFiltro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
