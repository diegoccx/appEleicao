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
import diego.com.github.modelo.Usuario;


/**
 * Classe DAO para acesso a entidade Votação
 * Diego Cardoso
 */




public class VotacaoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;

	
	
	
	public long inserirVotacao(CandidatoVotoDAO candidato) throws SQLException {
		String sql = null;
		long id_gerado = 0;
		estadoOperacao = false;
		connection = obterConexao();
		
	
		
		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO candidato(id_candidato_cargo, id_cargo_eleicao,nome, foto, quantidade_votos) VALUES(?, ?, ?, ?,?)";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			/*
			 * statement.setString(1, null); statement.setInt(2,
			 * candidato.getIdCargoEleicao()); statement.setString(3, candidato.getNome());
			 * statement.setBytes(4, candidato.getFoto()); statement.setInt(5,
			 * candidato.getQuantidadeVotos());
			 */
			
			
			
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
	
	
	public boolean alterarEleicao(Candidato candidato) throws SQLException {
		String sql = null;
		estadoOperacao = false;
		connection = obterConexao();

		
		
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE candidato SET nome = ?, id_cargo_eleicao = ?, foto = ?, quantidade_votos = ? WHERE id_candidato_cargo = ?";
			
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, candidato.getNome());
			statement.setInt(2, candidato.getIdCargoEleicao());
			
			statement.setBlob(3, candidato.getFoto());
			statement.setInt(4, candidato.getQuantidadeVotos());
			statement.setInt(5, candidato.getIdCandidato());
			
			
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

	


	//obter conexao 
	private Connection obterConexao() throws SQLException {
		return Conexao.getConnection();
	}

	}
