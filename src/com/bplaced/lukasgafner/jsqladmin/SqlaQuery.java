package com.bplaced.lukasgafner.jsqladmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class SqlaQuery {
	// Objects
	SqlaConnection sqlc = new SqlaConnection();
	
	// Query
	public ResultSet SQLDBQuery(String statement) {
		ResultSet dbrs = null;
		Statement stmt = null;
		
		try {
			stmt = sqlc.getDbconn().createStatement();
			dbrs = stmt.executeQuery(statement);
			
			sqlc.setConnstat(true);
			
			return dbrs;
							
		} catch (SQLException e) {
			// If ErrorCode = 0, then execute as an update Statement
			if (e.getErrorCode() == 0) {
				updateQuery(statement);
			} else {
				sqlErrMessage(e);
			}
			
			return null;
		}		
	}
	
	// Update der Datenbank
	private void updateQuery(String statement) {
		Statement stmt = null;
		try {
			stmt = sqlc.getDbconn().createStatement();
			stmt.executeUpdate(statement);
			
			sqlc.setConnstat(true);
			
		} catch (SQLException e) {
			sqlErrMessage(e);
		}
	}
	
	// Show error message
	public void sqlErrMessage(SQLException exception) {
		
		sqlc.setConnstat(false);
		
		String errmessage = "";
		
		switch (exception.getErrorCode()) {
		case 101:
			errmessage = "Query error \n" + exception.getMessage();
			break;
		case 1045:
			errmessage = "Wrong username or password";
			break;
		case 1044:
			errmessage = "Database not found";
			break;
		default:
			errmessage = exception.getMessage();
			break;
		}
		
		JOptionPane.showMessageDialog(null, errmessage + "\nErrorCode: " + exception.getErrorCode(), "Connection Error", JOptionPane.ERROR_MESSAGE);	
	}
}
