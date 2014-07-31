package bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import model.Ceratometria;
import model.Cliente;
import model.Lente;
import model.Refracao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;

import relatorio.ConnectionFactory;

import com.mysql.jdbc.Connection;

import dao.CeraDAO;
import dao.HibernateDAO;
import dao.LenteDAO;
import dao.RefracaoDAO;

@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cliente registro;
	private Cliente clienteSelecionado;
	private Cliente clienteRelatorio;
	private List<Cliente> listagem;

	private Refracao refracao;
	private List<Refracao> listaRefracao;

	private Lente lente;
	private List<Lente> listaLente;

	private Ceratometria cera;
	private List<Ceratometria> listaCeratometria;

	private String filtro;
	private int inicio;
	private int fim;

	public ClienteBean() {
		registro = new Cliente();
		refracao = new Refracao();
		lente = new Lente();
		cera = new Ceratometria();
		listaLente = null;
		listaCeratometria = null;
		clienteSelecionado = new Cliente();
		listaRefracao = null;
		listagem = null;
		filtro = new String();
		inicio = 0;
		fim = 0;
	}

	// METODOS DE NAVEGAï¿½ï¿½O...
	public String editar() {
		registro = clienteSelecionado;
		return "alterarCliente.xhtml?faces-redirect=true";
	}

	public String incluir() {
		try {
			HibernateDAO<Cliente, Integer> regHBR = new HibernateDAO<Cliente, Integer>(
					Cliente.class);
			Date hoje = new Date();
			registro.setDataCad(hoje);

			if (regHBR.inclui(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Obrigado.",
						"Registro incluido com sucesso."));
				return "/paginas/index.xhtml";
			}
		} catch (ConstraintViolationException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Chave primaria ja existe. Registro duplicado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na incluão dos dados."));
			e.printStackTrace();
		}
		return "incluirCliente.xhtml";

	}

	public String alterar() {
		try {
			HibernateDAO<Cliente, Integer> regHBR = new HibernateDAO<Cliente, Integer>(
					Cliente.class);
			if (regHBR.altera(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "INFO!",
						"Registro alterado com sucesso.Confira abaixo"));
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
		return "/paginas/index.xhtml?faces-redirect=true";

	}

	public void consultar(ActionEvent event) {
		try {
			HibernateDAO<Cliente, Integer> regHBR = new HibernateDAO<Cliente, Integer>(
					Cliente.class);
			registro = (Cliente) regHBR.consulta(registro.getCodCliente());
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
			HibernateDAO<Cliente, Integer> regHBR = new HibernateDAO<Cliente, Integer>(
					Cliente.class);
			listagem = regHBR.listaTudo();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public String deletar(Cliente up) {
		try {
			registro = up;
			System.out.println("Id Up" + registro.getCodCliente());
			HibernateDAO<Cliente, Integer> regHBR2 = new HibernateDAO<Cliente, Integer>(
					Cliente.class);
			registro = (Cliente) regHBR2.consulta(registro.getCodCliente());
			HibernateDAO<Cliente, Integer> regHBR = new HibernateDAO<Cliente, Integer>(
					Cliente.class);
			if (regHBR.exclui(registro)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "INFO!",
						"Registro excluï¿½do com sucesso. Confira Abaixo!!!"));
				return "listarUp.xhtml";
			}
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Objeto nï¿½o localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na exclusï¿½o dos dados."));
			e.printStackTrace();
		}
		return "/paginas/up/consultar.xhtml";
	}

	public void listarTudo(ActionEvent event) {
		try {
			HibernateDAO<Cliente, Integer> regHBR = new HibernateDAO<Cliente, Integer>(
					Cliente.class);
			listagem = regHBR.listaTudo();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void gerarRelatorio() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		ServletContext scontext = (ServletContext) facesContext
				.getExternalContext().getContext();

		StringBuffer pathRelatorios = new StringBuffer();
		pathRelatorios.append(scontext.getRealPath("/relatorios/"));

		System.out.println(pathRelatorios);

		pathRelatorios.append(File.separator);
		System.out.println(pathRelatorios);
		@SuppressWarnings("rawtypes")
		Map parametros = new HashMap<>();
		parametros.put("SUBREPORT_DIR", pathRelatorios);
		parametros.put("ID_CLIENTE", clienteSelecionado.getCodCliente());
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					pathRelatorios + "atendimentos.jasper", parametros, con);
			byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpServletResponse res = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();
			res.setContentType("application/pdf");
			int codigo = (int) (Math.random() * 1000);
			res.setHeader("Content-disposition", "inline);filename=relatorio_"
					+ codigo + ".pdf");
			res.getOutputStream().write(b);
			res.getCharacterEncoding();
			facesContext.responseComplete();
			con.close();
		} catch (JRException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void listarPagina(ActionEvent event) {
		try {
			HibernateDAO<Cliente, Integer> regHBR = new HibernateDAO<Cliente, Integer>(
					Cliente.class);
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
			HibernateDAO<Cliente, Integer> regHBR = new HibernateDAO<Cliente, Integer>(
					Cliente.class);
			listagem = regHBR.listaLike("nome", "%" + filtro + "%");
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void buscarHistorico() {
		try {
			RefracaoDAO refracaoDao = new RefracaoDAO();
			listaRefracao = refracaoDao.listarRefracao(clienteRelatorio
					.getCodCliente());

			LenteDAO lenteDao = new LenteDAO();
			listaLente = lenteDao
					.listarLentes(clienteRelatorio.getCodCliente());

			CeraDAO ceraDao = new CeraDAO();
			listaCeratometria = ceraDao.listarCeratometria(clienteRelatorio
					.getCodCliente());

		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Falha na consulta aos dados."));
			e.printStackTrace();
		}
	}

	public void excluirRefracao(Refracao refra) {
		try {
			//HibernateDAO<Refracao, Integer> regHBR2 = new HibernateDAO<Refracao, Integer>(Refracao.class);
			// registro = (Refracao) regHBR2.consulta(registro.getIdRefracao());
			HibernateDAO<Refracao, Integer> regHBR = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
			if (regHBR.exclui(refra)) {
				RefracaoDAO refracaoDao = new RefracaoDAO();
				listaRefracao = refracaoDao.listarRefracao(clienteRelatorio
						.getCodCliente());
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

	public void alterarRefracao(Refracao refra) {
		try {
			HibernateDAO<Refracao, Integer> regHBR = new HibernateDAO<Refracao, Integer>(
					Refracao.class);
			if (regHBR.altera(refra)) {
				RefracaoDAO refracaoDao = new RefracaoDAO();
				listaRefracao = refracaoDao.listarRefracao(clienteRelatorio
						.getCodCliente());
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "INFO!",
						"Registro alterado com sucesso."));

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

	
	public void excluirLentes(Lente lente) {
		try {
			//HibernateDAO<Lente, Integer> regHBR2 = new HibernateDAO<Lente, Integer>(Lente.class);
			//registro = (Lente) regHBR2.consulta(registro.getIdlentes());
			HibernateDAO<Lente, Integer> regHBR = new HibernateDAO<Lente, Integer>(Lente.class);
			if (regHBR.exclui(lente)) {
				LenteDAO lenteDao = new LenteDAO();
				listaLente = lenteDao
						.listarLentes(clienteRelatorio.getCodCliente());
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

	public void alterarLentes(Lente lente) {
		try {
			HibernateDAO<Lente, Integer> regHBR = new HibernateDAO<Lente, Integer>(Lente.class);
			if (regHBR.altera(lente)) {
				LenteDAO lenteDao = new LenteDAO();
				listaLente = lenteDao
						.listarLentes(clienteRelatorio.getCodCliente());
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!","Registro alterado com sucesso."));

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
	}
	
	public void excluirCeratometria(Ceratometria cera) {
		try {
			//HibernateDAO<Ceratometria, Integer> regHBR2 = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
			//registro = (Ceratometria) regHBR2.consulta(registro.getIdCeratometria());
			HibernateDAO<Ceratometria, Integer> regHBR = new HibernateDAO<Ceratometria, Integer>(Ceratometria.class);
			if (regHBR.exclui(cera)) {
				CeraDAO ceraDao = new CeraDAO();
				listaCeratometria = ceraDao.listarCeratometria(clienteRelatorio
						.getCodCliente());
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
	public Cliente getRegistro() {
		return registro;
	}

	public void setRegistro(Cliente registro) {
		this.registro = registro;
	}

	public List<Cliente> getListagem() {
		return listagem;
	}

	public void setListagem(List<Cliente> listagem) {
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

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Refracao getRefracao() {
		return refracao;
	}

	public void setRefracao(Refracao refracao) {
		this.refracao = refracao;
	}

	public List<Refracao> getListaRefracao() {
		return listaRefracao;
	}

	public void setListaRefracao(List<Refracao> listaRefracao) {
		this.listaRefracao = listaRefracao;
	}

	public Lente getLente() {
		return lente;
	}

	public void setLente(Lente lente) {
		this.lente = lente;
	}

	public List<Lente> getListaLente() {
		return listaLente;
	}

	public void setListaLente(List<Lente> listaLente) {
		this.listaLente = listaLente;
	}

	public Ceratometria getCera() {
		return cera;
	}

	public void setCera(Ceratometria cera) {
		this.cera = cera;
	}

	public List<Ceratometria> getListaCeratometria() {
		return listaCeratometria;
	}

	public void setListaCeratometria(List<Ceratometria> listaCeratometria) {
		this.listaCeratometria = listaCeratometria;
	}

	public Cliente getClienteRelatorio() {
		return clienteRelatorio;
	}

	public void setClienteRelatorio(Cliente clienteRelatorio) {
		this.clienteRelatorio = clienteRelatorio;
	}

}