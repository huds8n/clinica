package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the agenda database table.
 * 
 */
@Entity
@Table(name="agenda")
public class Agenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idagenda;

	@Temporal(TemporalType.DATE)
	@Column(name="data_agenda")
	private Date dataAgenda;

	@Temporal(TemporalType.DATE)
	@Column(name="data_lancamento")
	private Date dataLancamento;

	@Column(name="hora_agenda")
	private String horaAgenda;
	
	@Transient 
	private Date horaRetor;

	private String obs;

	private String status;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	public Agenda() {
	}

	public int getIdagenda() {
		return this.idagenda;
	}

	public void setIdagenda(int idagenda) {
		this.idagenda = idagenda;
	}

	public Date getDataAgenda() {
		return this.dataAgenda;
	}

	public void setDataAgenda(Date dataAgenda) {
		this.dataAgenda = dataAgenda;
	}

	public Date getDataLancamento() {
		return this.dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs.toUpperCase();
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status.toUpperCase();
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getHoraAgenda() {
		return horaAgenda;
	}

	public void setHoraAgenda(String horaAgenda) {
		this.horaAgenda = horaAgenda;
	}

	public Date getHoraRetor() {
		return horaRetor;
	}

	public void setHoraRetor(Date horaRetor) {
		this.horaRetor = horaRetor;
	}

}