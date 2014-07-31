package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the lentes database table.
 * 
 */
@Entity
@Table(name="lentes")
public class Lente implements Serializable , Comparable<Lente>  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idlentes;

	private String ao;

	private String contatologo;

	private String memo;

	private String cor;

	private String tecnico;

	private String dadosLente;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Temporal(TemporalType.DATE)
	private Date dtaEntrega;

	private String marca;

	private String odAv;

	private Float odCb;

	private Float odCilindro;

	private Integer odEixo;

	private Float odEsferico;

	private String oeAv;

	private Float oeCb;

	private Float oeCilindro;

	private Integer oeEixo;

	private Float oeEsferico;

	private String snlOdCilindro;

	private String snlOdEsferico;

	private String snlOeCilindro;

	private String snlOeEsferico;

	private String tipo;

	private boolean usaLentes;

	private String adicaoOe;

	private String adicaoOd;
	
	private String produtoLimpeza;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	public Lente() {
		this.idlentes = null;
		odCb = null;
		odCilindro = null;
		odEixo = null;
		oeEixo = null;
		odEsferico = null;
		oeCb = null;
		oeCilindro = null;
		oeEsferico = null;
	}
	
	@Override
	public int compareTo(Lente lente) {
		//return this.idRefracao.compareTo(refra.getIdRefracao());
		if (this.idlentes > lente.getIdlentes()) {
			return -1;
		}
		else if (this.idlentes < lente.getIdlentes()) {

			return 1;

		}
		return idlentes;

	}

	public Integer getIdlentes() {
		return idlentes;
	}

	public void setIdlentes(Integer idlentes) {
		this.idlentes = idlentes;
	}

	public String getAo() {
		return ao;
	}

	public void setAo(String ao) {
		this.ao = ao;
	}

	public String getContatologo() {
		return contatologo;
	}

	public void setContatologo(String contatologo) {
		this.contatologo = contatologo.toUpperCase();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo.toUpperCase();
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor.toUpperCase();
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico.toUpperCase();
	}

	public String getDadosLente() {
		return dadosLente;
	}

	public void setDadosLente(String dadosLente) {
		this.dadosLente = dadosLente.toUpperCase();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDtaEntrega() {
		return dtaEntrega;
	}

	public void setDtaEntrega(Date dtaEntrega) {
		this.dtaEntrega = dtaEntrega;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca.toUpperCase();
	}

	public String getOdAv() {
		return odAv;
	}

	public void setOdAv(String odAv) {
		this.odAv = odAv;
	}

	public Float getOdCb() {
		return odCb;
	}

	public void setOdCb(Float odCb) {
		this.odCb = odCb;
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

	public Float getOeCb() {
		return oeCb;
	}

	public void setOeCb(Float oeCb) {
		this.oeCb = oeCb;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase();
	}

	public boolean isUsaLentes() {
		return usaLentes;
	}

	public void setUsaLentes(boolean usaLentes) {
		this.usaLentes = usaLentes;
	}

	public String getAdicaoOe() {
		return adicaoOe;
	}

	public void setAdicaoOe(String adicaoOe) {
		this.adicaoOe = adicaoOe;
	}

	public String getAdicaoOd() {
		return adicaoOd;
	}

	public void setAdicaoOd(String adicaoOd) {
		this.adicaoOd = adicaoOd;
	}

	public String getProdutoLimpeza() {
		return produtoLimpeza;
	}

	public void setProdutoLimpeza(String produtoLimpeza) {
		this.produtoLimpeza = produtoLimpeza.toUpperCase();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}