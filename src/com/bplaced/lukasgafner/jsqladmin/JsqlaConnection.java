package com.bplaced.lukasgafner.jsqladmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class JsqlaConnection implements JsqlaConstants {
	// Objects
	JsqlaXMLFiles xmlf = new JsqlaXMLFiles();
	JsqlaProperties prop = new JsqlaProperties();
	
	// Static variables
	private static Connection dbconn;
	private static String database;
	private static String parameter;
	private static String user;
	private static String password;
	
	// Connect to database
	public void DBConnect() throws SQLException {
		String dburl = "";
		// TODO: different database types
		
		// MYSQL not available yet
		// dburl = "jdbc:mysql:" + database + parameter + "&user=" + user + "&password=" + password;
		
		// Sqlite
		dburl = "jdbc:sqlite:" + database;
		
		dbconn = DriverManager.getConnection(dburl);
		
	}
	
	// Read the connection from the xml file
	public void readConnection(String conname) {
		Element connectionsroot = xmlf.readXML(prop.getProperty(p_connectionsfile));
		NodeList connectionlist = connectionsroot.getElementsByTagName("connection");
		Element connection = null;
				
		for (int i = 0; i < connectionlist.getLength(); i++) {
			if (connectionlist.item(i).getAttributes().getNamedItem("cname").getNodeValue().equals(conname)) {
				connection = (Element)connectionlist.item(i);
			}
		}
		
		// Write the connection info into the variables
		database = connection.getElementsByTagName("database").item(0).getTextContent();
		parameter = connection.getElementsByTagName("parameter").item(0).getTextContent();
		user = connection.getElementsByTagName("user").item(0).getTextContent();
		password = connection.getElementsByTagName("password").item(0).getTextContent();
	}
	
	// TODO: Save connection in the xml file
	public void saveConnection() {
		// Not available yet
	}
	
	// Getters
	public final Connection getDbconn() {
		return dbconn;
	}
		
	public final String getDBPath() {
		return database;
	}
	
	public final String getDBParameter() {
		return parameter;
	}
	
	public final String getDBUser() {
		return user;
	}
	
	public final String getDBPassword() {
		return password;
	}
	
	// Setters
	public void setConnection(String dbpath, String dbparameter, String dbuser, String dbpassword) {
		database = dbpath;
		parameter = dbparameter;
		user = dbuser;
		password = dbpassword;
	}
}
