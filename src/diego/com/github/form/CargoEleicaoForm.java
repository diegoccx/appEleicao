package diego.com.github.form;

public class CargoEleicaoForm {
	
	private Integer idCargoEleicao;
	private Integer idEleicao;
	private String  nomeCargo;
	private String nomeEleicao;
	
	public CargoEleicaoForm () {
		
	}

	
    public String getCargoEleicaoNome() {
    	return nomeCargo + "--" + nomeEleicao;
    }
	public Integer getIdCargoEleicao() {
		return idCargoEleicao;
	}

	public void setIdCargoEleicao(Integer idCargoEleicao) {
		this.idCargoEleicao = idCargoEleicao;
	}

	public Integer getIdEleicao() {
		return idEleicao;
	}

	public void setIdEleicao(Integer idEleicao) {
		this.idEleicao = idEleicao;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public String getNomeEleicao() {
		return nomeEleicao;
	}

	public void setNomeEleicao(String nomeEleicao) {
		this.nomeEleicao = nomeEleicao;
	}

	
	
}
