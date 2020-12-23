package com.bplaced.lukasgafner.jsqladmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlaConnection {
	private static Connection dbconn;
	private static boolean connstat;
	
	//Verbindung zur Datenbank
	private void DBConnect() throws SQLException {
		String dburl = ""; // URL
		
		// MYSQL (oder andere) Verwendet den Pfad, den Connectionstring, den Benutzername und das Passwort
		dburl = "jdbc:mysql:" + cfg.getConfig("dbpath") + cfg.getConfig("dbstring") + "&user=" + JZHDBClient.dbuser + "&password=" + JZHDBClient.dbpassword;
		
		// Vebindet mit der Datenbank
		dbconn = DriverManager.getConnection(dburl);
	}
	
	// Getters
	public final Connection getDbconn() {
		return dbconn;
	}
	
	public final boolean getConnstat() {
		return connstat;
	}
	
	// Setters
	public void setConnstat(boolean status) {
		connstat = status;
	}
}
