package diego.com.github.modelo;

import java.sql.Date;

public class Eleicao {
	private int idEleicao;
	private String nome;
	private Date dataInicio;
	private Date dataFim;

	
	public Eleicao(int idEleicao, String nome, Date dataInicio, Date dataFim) {
		super();
		this.idEleicao = idEleicao;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	public Eleicao() {
		// TODO Auto-generated constructor stub
	}

	

	public int getIdEleicao() {
		return idEleicao;
	}

	public void setIdEleicao(int idEleicao) {
		this.idEleicao = idEleicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public String toString() {
		return "Eleicao [idEleicao=" + idEleicao + ", nome=" + nome + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + "]";
	}
}
	