package bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import model.Agenda;
import model.Cliente;
import model.Usuario;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;

import dao.AgendaDAO;
import dao.HibernateDAO;

@ManagedBean(name = "agendaBean")
@RequestScoped
public class AgendaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Agenda registro;
	private Agenda agendaSelecionada;
	private List<Agenda> listagem = new ArrayList<Agenda>();
	private Cliente cliente;
	private String filtro;
	private Date dataFiltro;


	private List<Cliente> listaFunc;
	private Integer idFuncSelect;
	private List<SelectItem> listaFuncs = new ArrayList<SelectItem>();

	public AgendaBean() {
		this.agendaSelecionada = new Agenda();
		this.registro = new Agenda();
		this.cliente = new Cliente();

	}

	// METODOS DE NAVEGA��O...
	public String editar() {
		registro = agendaSelecionada;
		return "alterarAgenda.xhtml";
	}

	public void listarFuncs() {
		try {
			HibernateDAO<Cliente, Integer> regHBR = new HibernateDAO<Cliente, Integer>(
					Cliente.class);
			listaFunc = regHBR.listaTudo();
			for (Cliente p : listaFunc)
				listaFuncs.add(new SelectItem(p.getCodCliente() - 1, p
						.getNome() + " " + p.getCpf()));
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void listarHoje() {
		try {
			AgendaDAO agendaDao = new AgendaDAO();
			listagem = agendaDao.listarHoje();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void buscarPorData(ActionEvent event) {
		try {
			AgendaDAO agendaDao = new AgendaDAO();
			listagem = agendaDao.buscarPorData(dataFiltro);
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public String incluir() {
		try {
			System.out
					.println("#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n#\n");
			converter();
			registro.setStatus("ATIVO");
			registro.setDataLancamento(new java.util.Date());
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);
			UsuarioBean controleLogin = (UsuarioBean) session
					.getAttribute("userBean");
			Usuario p = controleLogin.getUsuarioLogado();
			registro.setUsuario(p);
			HibernateDAO<Agenda, Integer> regHBR = new HibernateDAO<Agenda, Integer>(
					Agenda.class);
			registro.setCliente(cliente);
			if (regHBR.inclui(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "INFO!",
						"Registro incluido com sucesso."));
				return "listarAgenda.xhtml";
			}
		} catch (ConstraintViolationException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Chave prim�ria j� existe. Registro duplicado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na inclus�o dos dados."));
			e.printStackTrace();
		}
		return "/paginas/index.xhtml";

	}

	public void converter() {
		String mypattern1 = "HH:mm";
		SimpleDateFormat df = new SimpleDateFormat(mypattern1);
		String horaAgenda = df.format(registro.getHoraRetor());
		registro.setHoraAgenda(horaAgenda);
	}

	public void excluir(ActionEvent event) {
		try {
			HibernateDAO<Agenda, Integer> regHBR2 = new HibernateDAO<Agenda, Integer>(
					Agenda.class);
			registro = (Agenda) regHBR2.consulta(registro.getIdagenda());
			HibernateDAO<Agenda, Integer> regHBR = new HibernateDAO<Agenda, Integer>(
					Agenda.class);
			if (regHBR.exclui(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "INFO!",
						"Registro exclu�do com sucesso."));
			}
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Objeto n�o localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na exclus�o dos dados."));
			e.printStackTrace();
		}

	}

	public String alterar() {
		try {
			HibernateDAO<Agenda, Integer> regHBR = new HibernateDAO<Agenda, Integer>(
					Agenda.class);
			if (regHBR.altera(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "INFO!",
						"Registro alterado com sucesso."));
				return "listarAgenda.xhtml";
			}
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Objeto n�o localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na exclus�o dos dados."));
			e.printStackTrace();
		}
		return "/paginas/index.xhtml";
	}

	public void consultar(ActionEvent event) {
		try {
			HibernateDAO<Agenda, Integer> regHBR = new HibernateDAO<Agenda, Integer>(
					Agenda.class);
			registro = (Agenda) regHBR.consulta(registro.getIdagenda());
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Objeto n�o localizado."));
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
			HibernateDAO<Agenda, Integer> regHBR = new HibernateDAO<Agenda, Integer>(
					Agenda.class);
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
			HibernateDAO<Agenda, Integer> regHBR = new HibernateDAO<Agenda, Integer>(
					Agenda.class);
			listagem = regHBR.listaTudo();
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
			HibernateDAO<Agenda, Integer> regHBR = new HibernateDAO<Agenda, Integer>(
					Agenda.class);
			listagem = regHBR.listaLike("nome", filtro + "%");
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public Agenda getRegistro() {
		return registro;
	}

	public void setRegistro(Agenda registro) {
		this.registro = registro;
	}

	public List<Agenda> getListagem() {
		return listagem;
	}

	public void setListagem(List<Agenda> listagem) {
		this.listagem = listagem;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
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

	public Agenda getAgendaSelecionada() {
		return agendaSelecionada;
	}

	public void setAgendaSelecionada(Agenda agendaSelecionada) {
		this.agendaSelecionada = agendaSelecionada;
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
