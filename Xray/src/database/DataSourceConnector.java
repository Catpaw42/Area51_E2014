package database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceConnector {
	private static DataSource dataSource;

	/**
	 * @return Pooled Connection
	 * @throws SQLException - when connection fails
	 */
	public static Connection getConnection() throws SQLException{
		
		Connection connection = null;
		try {
			// Get DataSource from context.xml/web.xml file - look there... ONLY works in a servlet
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/s134000");
			connection = dataSource.getConnection();
		} catch (NamingException e) {
			//TODO handle exception...
			e.printStackTrace();
		}
		return connection;
		
	}
	public static void main(String[] args){
		try {
			Connection c = DataSourceConnector.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
