package org.i3.smartmeter.billing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private String connectionString;
	
	public void openConnection() throws SQLException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		connection = getJDBCConnection();
	}

	private Connection getJDBCConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		if(connectionString==null){
			connectionString  = getDefaultConnString();
		} 
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(connectionString);		
		return connection;
	}
	private String getDefaultConnString() {
		String connectionString = "jdbc:mysql://localhost:3306/smartmeterbilling?user=root&password=hack";
		return connectionString;
	}

	public void createStatement() throws SQLException{
		if(connection!=null){
			statement = connection.createStatement();
		}
	}
	public PreparedStatement createPrepareStatement(String sqlQuery) throws SQLException{
		if(connection!=null){
			preparedStatement = connection.prepareStatement(sqlQuery);
		}
		return preparedStatement;
	}
	public void releaseConnection() throws SQLException{
		if(statement!=null){
			statement.close();
		}
		if(preparedStatement!=null){
			preparedStatement.close();
		}
		if(connection!=null){
			connection.close();
		}
	}
	public void executeSimpleQuery(String sqlQuery) throws SQLException{
		createStatement();
		if(statement!=null){
			statement.execute(sqlQuery);
		}
	}
	public ResultSet executeSQLQuery(String sqlQuery) throws SQLException{
		ResultSet rs = null;
		createStatement();
		if(statement!=null){
			rs = statement.executeQuery(sqlQuery);
		}
		return rs;
	}
	
	public int executeUpdate(String sqlQuery) throws SQLException{
		int result=0;
		createStatement();
		if(statement!=null){
			result =  statement.executeUpdate(sqlQuery);
		}
		return result;
	}
	
	public int executeUpdatePS(PreparedStatement preparedStatement) throws SQLException{
		int result=0;
		if(preparedStatement!=null){
			result =  preparedStatement.executeUpdate();
		}
		return result;
	}
	public int[] executeBatchUpdate(PreparedStatement preparedStatement) throws SQLException{
		int[] result = null;
		if(preparedStatement!=null){
			result = preparedStatement.executeBatch();
		}
		return result;
	}
	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		connection.setAutoCommit(autoCommit);
	}
}
