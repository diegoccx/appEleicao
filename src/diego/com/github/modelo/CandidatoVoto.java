package diego.com.github.modelo;

public class CandidatoVoto {

	private int idCandidatoVoto;
	private int idCandidatoCargo;
	private int idUsuario;
	private String cpf;
	private String nome;
	private String protocolo;
	
	
	
	public CandidatoVoto(int idCandidatoVoto,
	int idCandidatoCargo,
	int idUsuario,
	String cpf,
	String nome,
	String protocolo) {
		super();
		this.idCandidatoVoto = idCandidatoVoto;
		this.idCandidatoCargo = idCandidatoCargo;
		this.idUsuario = idUsuario;
		this.cpf = cpf; 
		this.nome = nome;
		this.protocolo = protocolo;
		
	}
	
	public CandidatoVoto() {
		// TODO Auto-generated constructor stub
	}

	
    
	

	

	

	public int getIdCandidatoVoto() {
		return idCandidatoVoto;
	}

	public void setIdCandidatoVoto(int idCandidatoVoto) {
		this.idCandidatoVoto = idCandidatoVoto;
	}

	public int getIdCandidatoCargo() {
		return idCandidatoCargo;
	}

	public void setIdCandidatoCargo(int idCandidatoCargo) {
		this.idCandidatoCargo = idCandidatoCargo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	@Override
	public String toString() {
		return "CandidatoVoto [idCandidatoVoto=" + idCandidatoVoto + ", "
				+ "nome=" + nome + ","
				+ ", "
				+ "idUsuario=" + idUsuario 
				+ "cpf=" + cpf 
				+ "protocolo=" + protocolo +
				
				" idCandidatoCargo=" + idCandidatoCargo +"]";
		
	}
}


