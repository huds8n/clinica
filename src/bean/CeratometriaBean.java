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
import javax.faces.model.SelectItem;

import model.Ceratometria;
import model.Cliente;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;

import dao.HibernateDAO;

@ManagedBean(name = "ceraBean")
@ViewScoped
public class CeratometriaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Ceratometria registro;
	private Ceratometria CeratometriaSelecionada;
	private List<Ceratometria> listagem;
	private Cliente cliente;
	private String filtro;
	private Date dataFiltro;
	private int inicio;
	private int fim;

	private List<Cliente> listaFunc;
	private Integer idFuncSelect;
	private List<SelectItem> listaFuncs = new ArrayList<SelectItem>();


	public CeratometriaBean() {
		this.CeratometriaSelecionada = new Ceratometria();
		this.registro = new Ceratometria();
		cliente = new Cliente();
		this.listagem = null;
		this.filtro = new String();
		this.inicio = 0;
		this.fim = 0;


	}







	public String incluir() {
		try {
			HibernateDAO<Ceratometria, Integer> regHBR = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
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
			HibernateDAO<Ceratometria, Integer> regHBR2 = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
			registro = (Ceratometria) regHBR2.consulta(registro.getIdCeratometria());
			HibernateDAO<Ceratometria, Integer> regHBR = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
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
			HibernateDAO<Ceratometria, Integer> regHBR = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
			if (regHBR.altera(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!","Registro alterado com sucesso."));
				return "listarCeratometria.xhtml";
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
			HibernateDAO<Ceratometria, Integer> regHBR = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
			registro = (Ceratometria) regHBR.consulta(registro.getIdCeratometria());
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
			HibernateDAO<Ceratometria, Integer> regHBR = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
			listagem = regHBR.listaTudo();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void listarTudo() {
		try {
			HibernateDAO<Ceratometria, Integer> regHBR = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
			listagem = regHBR.listaTudo();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void listarPagina(ActionEvent event) {
		try {
			HibernateDAO<Ceratometria, Integer> regHBR = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
			listagem = regHBR.pagina(inicio, fim);
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!","Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void listarLike(ActionEvent event) {
		try {
			HibernateDAO<Ceratometria, Integer> regHBR = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
			listagem = regHBR.listaLike("nome", filtro+"%");
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!",	"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public Ceratometria getRegistro() {
		return registro;
	}

	public void setRegistro(Ceratometria registro) {
		this.registro = registro;
	}

	public List<Ceratometria> getListagem() {
		return listagem;
	}

	public void setListagem(List<Ceratometria> listagem) {
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

	public List<Cliente> getListaFunc() {
		return listaFunc;
	}

	public void setListaFunc(List<Cliente> listaFunc) {
		this.listaFunc = listaFunc;
	}

	public Integer getIdFuncSelect() {
		return idFuncSelect;
	}

	public void setIdFuncSelect(Integer idFuncSelect) {
		this.idFuncSelect = idFuncSelect;
	}

	public List<SelectItem> getListaFuncs() {
		return listaFuncs;
	}

	public void setListaFuncs(List<SelectItem> listaFuncs) {
		this.listaFuncs = listaFuncs;
	}

	public Ceratometria getCeratometriaSelecionada() {
		return CeratometriaSelecionada;
	}

	public void setCeratometriaSelecionada(Ceratometria CeratometriaSelecionada) {
		this.CeratometriaSelecionada = CeratometriaSelecionada;
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
