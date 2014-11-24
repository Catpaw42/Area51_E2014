package database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import database.interfaces.IDataSourceConnector;

public class DataSourceConnector implements IDataSourceConnector {
	protected static DataSource dataSource = null;

	/**
	 * @return Pooled Connection
	 * @throws ConnectionException when environment not set or connection to db fails.
	 */
	//TODO Should be used only in DAO's
	public static Connection getConnection() throws ConnectionException {	
		Connection connection = null;
		if (dataSource==null){
			try {
				// Get DataSource from context.xml/web.xml file - look there... ONLY works in a servlet
				Context initContext  = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/s134004");
			} catch (NamingException e) {
				e.printStackTrace();
				throw new ConnectionException("Server context environment error - check context.xml/web.xml" );
			}
		}
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConnectionException("Connection to database failed - dataSource.getConnection()");
		}
		return connection;

	}

}
