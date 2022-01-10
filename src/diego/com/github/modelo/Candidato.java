package diego.com.github.modelo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Candidato {

	private int idCandidato;
	private int idCargoEleicao;
	private InputStream foto ;
	private byte[] fotoImagem;
	private int quantidadeVotos;
	private String nome;
	private String codigoFoto;
	
	
	
	public Candidato(int idCandidato, int idCargoEleicao, InputStream foto,int quantidadeVotos,String nome) {
		super();
		this.idCandidato = idCandidato;
		this.idCargoEleicao = idCargoEleicao;
		this.foto = foto;
		this.quantidadeVotos = quantidadeVotos; 
		this.nome = nome;
		
	}
	
	public Candidato() {
		// TODO Auto-generated constructor stub
	}

	

	

	public byte[] getFotoImagem() {
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		try {
			while ((nRead = foto.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return buffer.toByteArray();
	}

	
	
	public void setFotoImagem(byte[] fotoImagem) {
		this.fotoImagem = fotoImagem;
	}

	public byte[] obterBytesImagem() throws IOException {
		
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();

				int nRead;
				byte[] data = new byte[16384];

				while ((nRead = foto.read(data, 0, data.length)) != -1) {
				  buffer.write(data, 0, nRead);
				}

				return buffer.toByteArray();
	}

	
	
	
	public String getCodigoFoto() {
		return codigoFoto;
	}

	public void setCodigoFoto(String codigoFoto) {
		this.codigoFoto = codigoFoto;
	}

	public int getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}

	public int getIdCargoEleicao() {
		return idCargoEleicao;
	}

	public void setIdCargoEleicao(int idCargoEleicao) {
		this.idCargoEleicao = idCargoEleicao;
	}

	public InputStream getFoto() {
		return foto;
	}

	public void setFoto(InputStream foto) {
		this.foto = foto;
	}

	public int getQuantidadeVotos() {
		return quantidadeVotos;
	}

	public void setQuantidadeVotos(int quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Candidato [idCandidato=" + idCandidato + ", nome=" + nome + ", idCargoEleicao=" + idCargoEleicao +"]";
	}
}

