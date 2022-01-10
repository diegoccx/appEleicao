package diego.com.github.modelo;

import java.util.Date;

public class Cargo {
	private int idCargoEleicao;
	private int idEleicao;
	private String nome;
	

	
	public Cargo(int idCargoEleicao, String nome) {
		super();
		this.idCargoEleicao = idCargoEleicao;
		this.nome = nome;
		this.idEleicao = idEleicao;
	}
	
	public Cargo() {
		// TODO Auto-generated constructor stub
	}

	

	

	public int getIdCargoEleicao() {
		return idCargoEleicao;
	}

	public void setIdCargoEleicao(int idCargoEleicao) {
		this.idCargoEleicao = idCargoEleicao;
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

	@Override
	public String toString() {
		return "Cargo [idCargoEleicao=" + idCargoEleicao + ", nome=" + nome + ", idEleicao=" + idEleicao +"]";
	}
}
