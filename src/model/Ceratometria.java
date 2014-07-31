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


@Entity
@Table(name="ceratometria")
public class Ceratometria implements Serializable  , Comparable<Ceratometria> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCeratometria;

	private Integer odEixo;

	private Float odHorizontal;

	private Float odVertical;

	private Integer oeEixo;

	private Float oeHorizontal;

	private Float oeVertical;

	@Temporal(TemporalType.DATE)
	private Date data;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	public Ceratometria() {
		odEixo = null;
		odHorizontal = null;
		odVertical = null;
		oeEixo = null;
		oeHorizontal = null;
		oeVertical = null;
	}
	
	@Override
	public int compareTo(Ceratometria cera) {
		//return this.idRefracao.compareTo(refra.getIdRefracao());
		if (this.idCeratometria > cera.getIdCeratometria()) {
			return -1;
		}
		else if (this.idCeratometria < cera.getIdCeratometria()) {

			return 1;

		}
		return idCeratometria;

	}

	
	public int getIdCeratometria() {
		return idCeratometria;
	}

	public void setIdCeratometria(int idCeratometria) {
		this.idCeratometria = idCeratometria;
	}

	public Integer getOdEixo() {
		return odEixo;
	}

	public void setOdEixo(Integer odEixo) {
		this.odEixo = odEixo;
	}

	public Float getOdHorizontal() {
		return odHorizontal;
	}

	public void setOdHorizontal(Float odHorizontal) {
		this.odHorizontal = odHorizontal;
	}

	public Float getOdVertical() {
		return odVertical;
	}

	public void setOdVertical(Float odVertical) {
		this.odVertical = odVertical;
	}

	public Integer getOeEixo() {
		return oeEixo;
	}

	public void setOeEixo(Integer oeEixo) {
		this.oeEixo = oeEixo;
	}

	public Float getOeHorizontal() {
		return oeHorizontal;
	}

	public void setOeHorizontal(Float oeHorizontal) {
		this.oeHorizontal = oeHorizontal;
	}

	public Float getOeVertical() {
		return oeVertical;
	}

	public void setOeVertical(Float oeVertical) {
		this.oeVertical = oeVertical;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

}