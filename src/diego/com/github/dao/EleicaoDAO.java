package diego.com.github.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import diego.com.github.conexao.Conexao;
import diego.com.github.modelo.Eleicao;
import diego.com.github.modelo.Usuario;

/**
 * Classe DAO para acesso a entidade Eleicao
 * Diego Cardoso
 */



public class EleicaoDAO {
private Connection connection;
private PreparedStatement statement;
private boolean estadoOperacao;

//registrar eleicao
public long inserirEleicao(Eleicao eleicao) throws SQLException {
	String sql = null;
	long id_gerado = 0;
	estadoOperacao = false;
	connection = obterConexao();
	
	try {
		connection.setAutoCommit(false);
		sql = "INSERT INTO eleicao(id_eleicao, nome, data_inicio, data_fim) VALUES(?, ?, ?, ?)";
		
		statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		statement.setString(1, null);
		statement.setString(2, eleicao.getNome());
		statement.setDate(3, eleicao.getDataInicio());
		statement.setDate(4, eleicao.getDataFim());
		
		estadoOperacao =  statement.executeUpdate() > 0;
		if (estadoOperacao == false) {
			throw new SQLException("Falha na criação da eleição");
		}

		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				id_gerado = generatedKeys.getLong(1);
			}
			else {
				throw new SQLException("Falha na criação da eleição, nenhum ID obtido.");
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
//editar eleição
public boolean alterarEleicao(Eleicao eleicao) throws SQLException {
	String sql = null;
	estadoOperacao = false;
	connection = obterConexao();

	try {
		connection.setAutoCommit(false);
		sql = "UPDATE eleicao SET nome = ?, data_inicio = ?, data_fim = ? WHERE id_eleicao = ?";
		
		statement = connection.prepareStatement(sql);
		
		
		statement.setString(1, eleicao.getNome());
		statement.setDate(2, eleicao.getDataInicio());
		statement.setDate(3, eleicao.getDataFim());
		statement.setInt(4, eleicao.getIdEleicao());
		
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



public Eleicao obterEleicao(Integer idEleicao) throws SQLException {
	ResultSet resultSet = null;

	Eleicao eleicao  = new Eleicao();

	String sql = null;
	estadoOperacao = false;
	connection = obterConexao();
	

	try {
		sql = "SELECT * FROM eleicao WHERE id_eleicao = ? ";
		statement=connection.prepareStatement(sql);
		
		statement.setInt(1, idEleicao);
		resultSet = statement.executeQuery();
		

		if(resultSet.next()) {	
			
			eleicao.setIdEleicao(resultSet.getInt(1));
			eleicao.setNome(resultSet.getString(2));
			eleicao.setDataInicio(resultSet.getDate(3));
			eleicao.setDataFim(resultSet.getDate(4));
			
			
			
		}
		statement.close();
		resultSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}finally{
		System.out.println("fechou eleicao"+eleicao.getNome());
		
		connection.close();
	}

	return eleicao;
}

public List<Eleicao> listarEleicoes() throws SQLException {
	ResultSet resultSet = null;

	Eleicao eleicao ;

	String sql = null;
	estadoOperacao = false;
	connection = obterConexao();
	List<Eleicao> eleicoes = new ArrayList<Eleicao>();

	try {
		sql = "SELECT * FROM eleicao ";
		statement=connection.prepareStatement(sql);
		
		resultSet = statement.executeQuery();

		while(resultSet.next()) {	
			eleicao = new Eleicao();
			eleicao.setIdEleicao(resultSet.getInt(1));
			eleicao.setNome(resultSet.getString(2));
			eleicao.setDataInicio(resultSet.getDate(3));
			eleicao.setDataFim(resultSet.getDate(4));
			
			eleicoes.add(eleicao);
			
		}
		statement.close();
		resultSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}finally{
		System.out.println("fechou"+eleicoes.size());
		
		connection.close();
	}

	return eleicoes;
}


//obter conexao 
private Connection obterConexao() throws SQLException {
	return Conexao.getConnection();
}

}
