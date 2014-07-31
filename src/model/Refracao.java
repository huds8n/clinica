package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the refracao database table.
 * 
 */
@Entity
@Table(name = "refracao")
public class Refracao implements Serializable, Comparable<Refracao> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRefracao;

	private Float adcao;

	private String ao;

	private String crm;

	private String dr;

	@Temporal(TemporalType.DATE)
	private Date dta;

	@Temporal(TemporalType.DATE)
	private Date data;

	private String obs;

	private String odAv;

	private Float odCilindro;

	private Integer odEixo;

	private Float odEsferico;

	private String oeAv;

	private Float oeCilindro;

	private Integer oeEixo;

	private Float oeEsferico;

	private String sinal;

	private String snlOdCilindro;

	private String snlOdEsferico;

	private String snlOeCilindro;

	private String snlOeEsferico;

	private String memo;

	// bi-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	// bi-directional many-to-one association to Medico
	@ManyToOne
	private Medico medico;

	public Refracao() {
		odCilindro = null;
		odEixo = null;
		oeCilindro = null;
		oeEixo = null;
		oeEsferico = null;
		odEsferico = null;
		adcao = null;
	}

	@Override
	public int compareTo(Refracao refra) {
		//return this.idRefracao.compareTo(refra.getIdRefracao());
		if (this.idRefracao > refra.getIdRefracao()) {
			return -1;
		}
		else if (this.idRefracao < refra.getIdRefracao()) {

			return 1;

		}
		return idRefracao;

	}

	public Integer getIdRefracao() {
		return idRefracao;
	}

	public void setIdRefracao(Integer idRefracao) {
		this.idRefracao = idRefracao;
	}

	public Float getAdcao() {
		return adcao;
	}

	public void setAdcao(Float adcao) {
		this.adcao = adcao;
	}

	public String getAo() {
		return ao;
	}

	public void setAo(String ao) {
		this.ao = ao;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm.toUpperCase();
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr.toUpperCase();
	}

	public Date getDta() {
		return dta;
	}

	public void setDta(Date dta) {
		this.dta = dta;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs.toUpperCase();
	}

	public String getOdAv() {
		return odAv;
	}

	public void setOdAv(String odAv) {
		this.odAv = odAv;
	}

	public Float getOdCilindro() {
		return odCilindro;
	}

	public void setOdCilindro(Float odCilindro) {
		this.odCilindro = odCilindro;
	}

	public Integer getOdEixo() {
		return odEixo;
	}

	public void setOdEixo(Integer odEixo) {
		this.odEixo = odEixo;
	}

	public Float getOdEsferico() {
		return odEsferico;
	}

	public void setOdEsferico(Float odEsferico) {
		this.odEsferico = odEsferico;
	}

	public String getOeAv() {
		return oeAv;
	}

	public void setOeAv(String oeAv) {
		this.oeAv = oeAv;
	}

	public Float getOeCilindro() {
		return oeCilindro;
	}

	public void setOeCilindro(Float oeCilindro) {
		this.oeCilindro = oeCilindro;
	}

	public Integer getOeEixo() {
		return oeEixo;
	}

	public void setOeEixo(Integer oeEixo) {
		this.oeEixo = oeEixo;
	}

	public Float getOeEsferico() {
		return oeEsferico;
	}

	public void setOeEsferico(Float oeEsferico) {
		this.oeEsferico = oeEsferico;
	}

	public String getSinal() {
		return sinal;
	}

	public void setSinal(String sinal) {
		this.sinal = sinal;
	}

	public String getSnlOdCilindro() {
		return snlOdCilindro;
	}

	public void setSnlOdCilindro(String snlOdCilindro) {
		this.snlOdCilindro = snlOdCilindro;
	}

	public String getSnlOdEsferico() {
		return snlOdEsferico;
	}

	public void setSnlOdEsferico(String snlOdEsferico) {
		this.snlOdEsferico = snlOdEsferico;
	}

	public String getSnlOeCilindro() {
		return snlOeCilindro;
	}

	public void setSnlOeCilindro(String snlOeCilindro) {
		this.snlOeCilindro = snlOeCilindro;
	}

	public String getSnlOeEsferico() {
		return snlOeEsferico;
	}

	public void setSnlOeEsferico(String snlOeEsferico) {
		this.snlOeEsferico = snlOeEsferico;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo.toUpperCase();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}