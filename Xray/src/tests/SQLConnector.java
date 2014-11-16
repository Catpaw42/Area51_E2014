package tests;

import java.sql.*;

public class SQLConnector{
	private Connection connection;
	private String server;
	private String port;
	private String database;
	private String username;
	private String password;

	public SQLConnector(){
		
		server = "sql-lab1.cc.dtu.dk";
		port = "3306";
		database = "s134004";
		username = "s134004";
		password = "750inurerbest";
		loadDriver();
	}

	public SQLConnector(String server, String port,
			String database, String username, String password){
		this.server = server;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		loadDriver(); 
	}

	public PreparedStatement getPreparedStatement(String sqlStatement){
		return getPreparedStatement(sqlStatement, -1);
	}
	
	public PreparedStatement getPreparedStatement(String sqlStatement, int extraArg){
		PreparedStatement prepStatement = null;
		try{
			if(extraArg != -1)
				prepStatement = getConnection().prepareStatement(sqlStatement, extraArg);
			else
			prepStatement = getConnection().prepareStatement(sqlStatement);
		} catch (SQLException e){
			System.err.println("sql exception while getting prepared statement");
		} 
		return prepStatement;
	}
	
	
	public Connection getConnection(){
		if (connection == null){
			this.connection = connect();
			return this.connection;
		}
		boolean isAlive = false;
		try {
			isAlive = this.connection.isValid(1);
		} catch (SQLException e) {
			System.err.println("sql exception");
		}
		if (isAlive){
		return this.connection;
		} 
		return connect();

	}
	
	private Connection connect() {
		if (!sqlConnect()) System.err.println("connection failed");;
		return this.connection;
	}

	private boolean loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("No mySQL driver found!" + e.getMessage());
		}
		return false;
	}

	private boolean sqlConnect()  {
		try {
			this.connection = DriverManager
					.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database , username,password);
			return true;
		} catch (SQLException e) {
			System.err.println("Connection to DB failed!");
		}
		return false;
	}

	

}
