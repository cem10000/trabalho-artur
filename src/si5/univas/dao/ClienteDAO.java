package si5.univas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import si5.univas.model.Cliente;

public class ClienteDAO {

	private Connection connection;
	
	public ClienteDAO(Connection connection) {
		this.connection = connection;
	}
	
	private int nextCode() throws DAOException {
		
		try {
			String sql = "SELECT nextval('seq_cliente')";
			ResultSet result = connection.createStatement().executeQuery(sql);

			if(result.next()) {
				return result.getInt(1);
			}
			
			throw new DAOException("N�o foi poss�vel pegar o valor da sequ�ncia");
		} catch (SQLException exception) {
			throw new DAOException(exception);
		}
	}
	
	public void insertCliente(Cliente cliente)throws DAOException{
		
			try {
				// Buscando o pr�ximo valor da sequ�ncia e atribuindo ao objeto
				cliente.setCod(nextCode());
				
				String sql = "INSERT INTO cliente (nome,email) VALUES (?,?)";
				
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1,cliente.getNome());
				statement.setString(2,cliente.getEmail());
				
				statement.executeUpdate();
			} catch (SQLException exception) {
				throw new DAOException(exception);
			}
		}
	}
	

