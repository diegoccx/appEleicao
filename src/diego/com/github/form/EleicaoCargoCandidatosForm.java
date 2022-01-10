package diego.com.github.form;

import java.util.List;

import diego.com.github.modelo.Candidato;

public class EleicaoCargoCandidatosForm {
	
	
	private Integer idEleicao;
	private String  nomeEleicao;
	
	private Integer idCargo;
	private String  nomeCargo;
	
	private List<Candidato> candidatos;
	
	
	
	
	
	public Integer getIdEleicao() {
		return idEleicao;
	}





	public void setIdEleicao(Integer idEleicao) {
		this.idEleicao = idEleicao;
	}





	public String getNomeEleicao() {
		return nomeEleicao;
	}





	public void setNomeEleicao(String nomeEleicao) {
		this.nomeEleicao = nomeEleicao;
	}





	public Integer getIdCargo() {
		return idCargo;
	}





	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
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





	public EleicaoCargoCandidatosForm() {
		
		
	}

}
