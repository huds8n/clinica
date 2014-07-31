package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import model.Cliente;
import model.Lente;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;

import dao.HibernateDAO;

@ManagedBean(name = "lenteBean")
@ViewScoped
public class LenteBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Lente registro;
	private Lente LenteSelecionada;
	private List<Lente> listagem = new ArrayList<Lente>();
	private Cliente cliente;
	private String filtro;
	private Date dataFiltro;
	private int inicio;
	private int fim;



	public LenteBean() {
		this.LenteSelecionada = new Lente();
		this.registro = new Lente();
		this.cliente = new Cliente();
		this.filtro = new String();



	}







	public String incluir() {
		try {
			HibernateDAO<Lente, Integer> regHBR = new HibernateDAO<Lente, Integer>(Lente.class);
			registro.setData(new java.util.Date());
			registro.setCliente(cliente);
			if (regHBR.inclui(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!","Registro incluido com sucesso."));
				return "iniciarConsulta2.xhtml";
			}
		} catch (ConstraintViolationException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!",	"Chave primária já existe. Registro duplicado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!",	"Falha na inclusão dos dados."));
			e.printStackTrace();
		}

		return "iniciarConsulta2.xhtml";
	}


	public void excluir(ActionEvent event) {
		try {
			HibernateDAO<Lente, Integer> regHBR2 = new HibernateDAO<Lente, Integer>(Lente.class);
			registro = (Lente) regHBR2.consulta(registro.getIdlentes());
			HibernateDAO<Lente, Integer> regHBR = new HibernateDAO<Lente, Integer>(Lente.class);
			if (regHBR.exclui(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!","Registro excluído com sucesso."));
			}
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!",	"Objeto não localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Falha na exclusão dos dados."));
			e.printStackTrace();
		}

	}

	public String alterar() {
		try {
			HibernateDAO<Lente, Integer> regHBR = new HibernateDAO<Lente, Integer>(Lente.class);
			if (regHBR.altera(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!","Registro alterado com sucesso."));
				return "listarLente.xhtml";
			}
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Objeto não localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Falha na exclusão dos dados."));
			e.printStackTrace();
		}
		return "/paginas/index.xhtml";
	}

	public void consultar(ActionEvent event) {
		try {
			HibernateDAO<Lente, Integer> regHBR = new HibernateDAO<Lente, Integer>(Lente.class);
			registro = (Lente) regHBR.consulta(registro.getIdlentes());
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Objeto não localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Falha de consulta aos dados."));
			e.printStackTrace();
		}

	}

	public void listarTudo(ActionEvent event) {
		try {
			HibernateDAO<Lente, Integer> regHBR = new HibernateDAO<Lente, Integer>(Lente.class);
			listagem = regHBR.listaTudo();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void listarTudo() {
		try {
			HibernateDAO<Lente, Integer> regHBR = new HibernateDAO<Lente, Integer>(Lente.class);
			listagem = regHBR.listaTudo();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void listarPagina(ActionEvent event) {
		try {
			HibernateDAO<Lente, Integer> regHBR = new HibernateDAO<Lente, Integer>(Lente.class);
			listagem = regHBR.pagina(inicio, fim);
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void listarLike(ActionEvent event) {
		try {
			HibernateDAO<Lente, Integer> regHBR = new HibernateDAO<Lente, Integer>(Lente.class);
			listagem = regHBR.listaLike("nome", filtro+"%");
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!",	"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public Lente getRegistro() {
		return registro;
	}

	public void setRegistro(Lente registro) {
		this.registro = registro;
	}

	public List<Lente> getListagem() {
		return listagem;
	}

	public void setListagem(List<Lente> listagem) {
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

	
	public Lente getLenteSelecionada() {
		return LenteSelecionada;
	}

	public void setLenteSelecionada(Lente LenteSelecionada) {
		this.LenteSelecionada = LenteSelecionada;
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
