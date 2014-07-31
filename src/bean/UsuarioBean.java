package bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Usuario;
import util.UtilErros;
import util.UtilMensagens;
import dao.UsuarioDAO;

@ManagedBean(name = "userBean")
@SessionScoped
public class UsuarioBean implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private UsuarioDAO dao;
	private Usuario usuarioLogado;
	private String usuario;
	private String senha;



	public UsuarioBean() {
	

	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String efetuarLogin() {
		dao = new UsuarioDAO();
		try {

			usuarioLogado = dao.login(usuario, senha);
			System.out.println("USUARIO LOGADO: " + usuarioLogado);
			if (usuarioLogado.getIdusuario() != null) {

				UtilMensagens.mensagemInformacao("Login efetuado com sucesso! \n Seja Bem Vindo: \n"+usuarioLogado.getLogin());
				return "/paginas/index.xhtml?faces-redirect=true";
			} else {
				UtilMensagens.mensagemErro("Login não efetuado com sucesso!"
						+ " Usuario ou senha invalidos!");
				return "/login.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			UtilErros.getMensagemErro(e);

		}finally{
			dao = null;
		}
		return "/login.xhtml?faces-redirect=true";

	}

	
	public String logout() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.invalidate();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logoff!","Logoff Efetuado..."));
		return "/login.xhtml?faces-redirect=true";
	}


	


}
