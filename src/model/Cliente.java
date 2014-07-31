package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codCliente;

	private String celular;

	private String cep;

	private String cidade;

	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name="data_cad")
	private Date dataCad;

	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;

	private String endereco;

	private String municipio;

	private String nome;

	private String obs;

	private String ocupacao;

	private String orgaoExp;

	private String rg;

	private String telefone;

	private String txtMem;

	private String uf;

	private String ufOrgao;

	//bi-directional many-to-one association to Agenda
	@OneToMany(mappedBy="cliente")
	private List<Agenda> agendas;

	//bi-directional many-to-one association to Ceratometria
	@OneToMany(mappedBy="cliente")
	private List<Ceratometria> ceratometrias;

	//bi-directional many-to-one association to Lente
	@OneToMany(mappedBy="cliente")
	private List<Lente> lentes;

	//bi-directional many-to-one association to Refracao
	@OneToMany(mappedBy="cliente")
	private List<Refracao> refracaos;

	public Cliente() {
		uf = "DF";
	}

	public int getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular.toUpperCase();
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep.toUpperCase();
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade.toUpperCase();
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.toUpperCase();
	}

	public Date getDataCad() {
		return this.dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco.toUpperCase();
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio.toUpperCase();
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs.toUpperCase();
	}

	public String getOcupacao() {
		return this.ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao.toUpperCase();
	}

	public String getOrgaoExp() {
		return this.orgaoExp;
	}

	public void setOrgaoExp(String orgaoExp) {
		this.orgaoExp = orgaoExp.toUpperCase();
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg.toUpperCase();
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone.toUpperCase();
	}

	public String getTxtMem() {
		return this.txtMem;
	}

	public void setTxtMem(String txtMem) {
		this.txtMem = txtMem.toUpperCase();
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf.toUpperCase();
	}

	public String getUfOrgao() {
		return this.ufOrgao;
	}

	public void setUfOrgao(String ufOrgao) {
		this.ufOrgao = ufOrgao.toUpperCase();
	}

	public List<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public Agenda addAgenda(Agenda agenda) {
		getAgendas().add(agenda);
		agenda.setCliente(this);

		return agenda;
	}

	public Agenda removeAgenda(Agenda agenda) {
		getAgendas().remove(agenda);
		agenda.setCliente(null);

		return agenda;
	}

	public List<Ceratometria> getCeratometrias() {
		return this.ceratometrias;
	}

	public void setCeratometrias(List<Ceratometria> ceratometrias) {
		this.ceratometrias = ceratometrias;
	}

	public Ceratometria addCeratometria(Ceratometria ceratometria) {
		getCeratometrias().add(ceratometria);
		ceratometria.setCliente(this);

		return ceratometria;
	}

	public Ceratometria removeCeratometria(Ceratometria ceratometria) {
		getCeratometrias().remove(ceratometria);
		ceratometria.setCliente(null);

		return ceratometria;
	}

	public List<Lente> getLentes() {
		return this.lentes;
	}

	public void setLentes(List<Lente> lentes) {
		this.lentes = lentes;
	}

	public Lente addLente(Lente lente) {
		getLentes().add(lente);
		lente.setCliente(this);

		return lente;
	}

	public Lente removeLente(Lente lente) {
		getLentes().remove(lente);
		lente.setCliente(null);

		return lente;
	}

	public List<Refracao> getRefracaos() {
		return this.refracaos;
	}

	public void setRefracaos(List<Refracao> refracaos) {
		this.refracaos = refracaos;
	}

	public Refracao addRefracao(Refracao refracao) {
		getRefracaos().add(refracao);
		refracao.setCliente(this);

		return refracao;
	}

	public Refracao removeRefracao(Refracao refracao) {
		getRefracaos().remove(refracao);
		refracao.setCliente(null);

		return refracao;
	}

}