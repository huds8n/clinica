package model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

import java.util.List;



@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@IndexColumn(name = "idusuario_UNIQUE")
	@Id
	@Column(name = "idusuario", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idusuario;

	private String login;

	private int nivel;

	private String senha;

	//bi-directional many-to-one association to Agenda
	@OneToMany(mappedBy="usuario")
	private List<Agenda> agendas;

	public Usuario() {
		this.idusuario = null;
	}
	
	@Override
	public String toString() {
		return "\nUsuario \nidUsuario=" + idusuario + ", \nLogin="
				+ login;
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getNivel() {
		return this.nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public Agenda addAgenda(Agenda agenda) {
		getAgendas().add(agenda);
		agenda.setUsuario(this);

		return agenda;
	}

	public Agenda removeAgenda(Agenda agenda) {
		getAgendas().remove(agenda);
		agenda.setUsuario(null);

		return agenda;
	}

}