package com.bplaced.lukasgafner.jsqladmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class JsqlaQuery {
	// Objects
	JsqlaConnection sqlc = new JsqlaConnection();
	JsqlaExpressions expr = new JsqlaExpressions();
	
	// Query
	public ResultSet SQLDBQuery(String statement) {
		ResultSet dbrs = null;
		Statement stmt = null;
		
		try {
			stmt = sqlc.getDbconn().createStatement();
			dbrs = stmt.executeQuery(statement);
									
		} catch (SQLException e) {
			String[] nonerrors = expr.getNonerrors();
			boolean iserror = true;
			for (String s: nonerrors) {
				if (s.equals(Integer.toString(e.getErrorCode()))) {
					iserror = false;
				}
			}
			if (iserror) {
				sqlErrMessage(e);
			}
		}
		
		return dbrs;
	}
	
	// Show error message
	public void sqlErrMessage(SQLException exception) {
		JOptionPane.showMessageDialog(null, "ErrorCode: " + exception.getErrorCode() + "/n" + exception.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);	
	}
}
