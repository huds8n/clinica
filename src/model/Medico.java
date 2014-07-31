package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the medico database table.
 * 
 */
@Entity
@Table(name="medico")
public class Medico implements Serializable,  Comparable<Medico> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmedico;

	private String crm;

	private String nome;

	//bi-directional many-to-one association to Refracao
	@OneToMany(mappedBy="medico")
	private List<Refracao> refracaos;

	public Medico() {
	}

	
	@Override
	public String toString() {
		return nome + "- CRM: " + crm;
	}
	public int getIdmedico() {
		return this.idmedico;
	}

	public void setIdmedico(int idmedico) {
		this.idmedico = idmedico;
	}

	public String getCrm() {
		return this.crm;
	}

	public void setCrm(String crm) {
		this.crm = crm.toUpperCase();
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public List<Refracao> getRefracaos() {
		return this.refracaos;
	}

	public void setRefracaos(List<Refracao> refracaos) {
		this.refracaos = refracaos;
	}

	public Refracao addRefracao(Refracao refracao) {
		getRefracaos().add(refracao);
		refracao.setMedico(this);

		return refracao;
	}

	public Refracao removeRefracao(Refracao refracao) {
		getRefracaos().remove(refracao);
		refracao.setMedico(null);

		return refracao;
	}


	@Override
	public int compareTo(Medico medico) {
		return this.nome.compareTo(medico.getNome());
	}

}