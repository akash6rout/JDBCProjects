package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SelectTest_PropertiesFile {

	public static void main(String[] args) {
		
		//Locate the properties file
		try(InputStream is=new FileInputStream("src/com/nt/commons/jdbc.properties")){
		
		//Load properties file content into java.util.Proprties class object
			Properties props=new Properties();
			props.load(is);
			
			try(Connection con=DriverManager.getConnection(props.getProperty("jdbc.url"),
																		props.getProperty("jdbc.user"),
																		props.getProperty("jdbc.pwd"));
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("SELECT * FROM STUDENT");
					){
				if(rs!=null) {
					while(rs.next()) {
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					}//while
				}//if
			}//try2
			
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
