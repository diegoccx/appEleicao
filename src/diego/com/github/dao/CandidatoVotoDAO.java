package diego.com.github.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import diego.com.github.conexao.Conexao;
import diego.com.github.modelo.Candidato;
import diego.com.github.modelo.CandidatoVoto;
import diego.com.github.modelo.Cargo;
import diego.com.github.modelo.Usuario;


/**
 * Classe DAO para acesso a entidade CandidatoDAO
 * Diego Cardoso
 */



public class CandidatoVotoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;


	
	
	public long inserirCandidatoVoto(CandidatoVoto candidatoVoto) throws SQLException {
		String sql = null;
		long id_gerado = 0;
		estadoOperacao = false;
		connection = obterConexao();
		
		
		
		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO candidato_voto(id_candidato_voto,"
					+ " id_candidato_cargo,id_usuario, nome,cpf,protocolo) VALUES(?, ?, ?, ?,?,?)";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, null);
			statement.setInt(2, candidatoVoto.getIdCandidatoCargo());
			statement.setInt(3, candidatoVoto.getIdUsuario());
			statement.setString(4, candidatoVoto.getNome());
			statement.setString(5, candidatoVoto.getCpf());
			statement.setString(6, candidatoVoto.getProtocolo());
			
			
			
			estadoOperacao =  statement.executeUpdate() > 0;
			if (estadoOperacao == false) {
				throw new SQLException("Falha na criação do candidato");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					id_gerado = generatedKeys.getLong(1);
				}
				else {
					throw new SQLException("Falha na criação do candidato, nenhum ID obtido.");
				}
			}
			
			connection.commit();
			statement.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}finally{
			System.out.println("fechou");
			connection.close();
		}
		
		return id_gerado;
	}
	//editar candidato
	
	
	
	
	public boolean alterarEleicao(CandidatoVoto candidatoVoto) throws SQLException {
		String sql = null;
		estadoOperacao = false;
		connection = obterConexao();

		
		
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE candidato_voto SET nome = ?, id_candidato_cargo = ?,"
					+ " id_usuario = ?, cpf = ?, protocolo = ? WHERE id_candidato_voto = ?";
			
			statement = connection.prepareStatement(sql);
			
			statement.setString(6, null);
			statement.setInt(2, candidatoVoto.getIdCandidatoCargo());
			statement.setInt(3, candidatoVoto.getIdUsuario());
			statement.setString(1, candidatoVoto.getNome());
			statement.setString(4, candidatoVoto.getCpf());
			statement.setString(5, candidatoVoto.getProtocolo());
			
			
			estadoOperacao = statement.executeUpdate() > 0 ;
			connection.commit();
			statement.close();
			
			
		}
		catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}finally{
			System.out.println("fechou");
			connection.close();
		}
		
		
		return estadoOperacao;
	}
		
	
	public boolean deletarUsuario(int id_usuario) throws SQLException {
		String sql = null;
		estadoOperacao = false;
		connection = obterConexao();

		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM usuario WHERE id_usuario = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id_usuario);
			
			estadoOperacao = statement.executeUpdate() > 0 ;
			connection.commit();
			statement.close();
			
			
		}
		catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}finally{
			System.out.println("fechou");
			connection.close();
		}
		
		
		return estadoOperacao;
	}

	
	
	public boolean jaVotou(String cpf) throws SQLException {
		ResultSet resultSet = null;
	    
		

		String sql = null;
		estadoOperacao = false;
		connection = obterConexao();
		List<Cargo> cargos = new ArrayList<Cargo>();

		try {
			sql = "SELECT * FROM candidato_voto  WHERE cpf = ?";
			statement=connection.prepareStatement(sql);
			statement.setString(1, cpf);
			resultSet = statement.executeQuery();
			

			if(resultSet.next()) {	

                    return true;
				
			}
			System.out.println("fechou cargos"+cargos.size());
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			
			
			connection.close();
		}

		return false;
	}

	


	//obter conexao 
	private Connection obterConexao() throws SQLException {
		return Conexao.getConnection();
	}

	}
