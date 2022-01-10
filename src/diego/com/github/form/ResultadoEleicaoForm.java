package diego.com.github.form;

import java.util.List;

import diego.com.github.modelo.Candidato;

public class ResultadoEleicaoForm {
	
	private String  nomeEleicao;
	
	
	private String  nomeCargo;
	
	private List<Candidato> candidatos;
	private boolean fimVotacao;
	public String getNomeEleicao() {
		return nomeEleicao;
	}
	public void setNomeEleicao(String nomeEleicao) {
		this.nomeEleicao = nomeEleicao;
	}
	public String getNomeCargo() {
		return nomeCargo;
	}
	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}
	public List<Candidato> getCandidatos() {
		return candidatos;
	}
	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	public boolean isFimVotacao() {
		return fimVotacao;
	}
	public void setFimVotacao(boolean fimVotacao) {
		this.fimVotacao = fimVotacao;
	}
	
	
	

}
