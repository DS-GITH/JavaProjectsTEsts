package CadastroELoginComplexo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BdConnection {

	    // Método para obter a conexão com o banco de dados
	    public static Connection getConnection() throws SQLException {
	        // URL do banco de dados MySQL com o nome do banco
	        String url = "jdbc:mysql://localhost:3306/login_cadastro"; // Ajuste "sistema_login" para o nome do seu banco
	        String username = "root"; // Substitua pelo seu usuário do MySQL
	        String password = "342007tecds"; // Substitua pela sua senha do MySQL

	        // Tenta se conectar ao banco de dados
	        return DriverManager.getConnection(url, username, password);
	    }
	}

	
	


