package relatorio;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionFactory {
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//String sql = "jdbc:mysql://192.168.25.40:3306/clinica";
			String sql = "jdbc:mysql://localhost:3306/clinica";
			return (Connection) DriverManager.getConnection(sql, "root", "root");
		}
		catch (ClassNotFoundException e) {
			throw new SQLException("Driver n�o localizado");
		}
	}
}