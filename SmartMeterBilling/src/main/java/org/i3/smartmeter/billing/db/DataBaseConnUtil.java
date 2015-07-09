package org.i3.smartmeter.billing.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class DataBaseConnUtil {
	
	private DataBaseConnection dbConn;
	private boolean autoCommit;

	public void setDbConn(DataBaseConnection dbConn){
		this.dbConn = dbConn;
	}
	
	public DataBaseConnUtil(){
		this.dbConn = new DataBaseConnection();
	}
	
	public DataBaseConnUtil(DataBaseConnection dbConn) {
		this.dbConn = dbConn;
	}

	public boolean execute(String sqlQuery) throws SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		dbConn.openConnection();
		dbConn.setAutoCommit(autoCommit);
		dbConn.executeSimpleQuery(sqlQuery);
		return true;
	}

	public List<Map<String, String>> executeSQLQuery(String sqlQuery)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		dbConn.openConnection();
		dbConn.setAutoCommit(autoCommit);
		ResultSet rs = dbConn.executeSQLQuery(sqlQuery);
		List<Map<String,String>> resultVector = new Vector<Map<String,String>>();
		while(rs.next()){
			Map<String,String> row = new HashMap<String,String>();
			ResultSetMetaData metaData = rs.getMetaData();
			for(int cnt=1;cnt<metaData.getColumnCount(); cnt++){
				String columnName = metaData.getColumnName(cnt);
				String columnValue = rs.getString(columnName);
				row.put(columnName, columnValue);
			}
			resultVector.add(row);
		}
		return resultVector;
	}
	public boolean executeUpdate(String sqlQuery) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		dbConn.openConnection();
		dbConn.setAutoCommit(autoCommit);
		dbConn.executeUpdate(sqlQuery);
		return true;
	}
	public void closeConnection() throws SQLException{
		dbConn.releaseConnection();
	}
	public void setAutoCommit(boolean autoCommit) throws SQLException{
		this.autoCommit = autoCommit;
	}
}
