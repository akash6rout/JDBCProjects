package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_LoginApp {
	private static final String AUTH_QUERY="SELECT COUNT(*) FROM USERINFO WHERE UNAME=? AND PWD=?";

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				PreparedStatement ps=con.prepareStatement(AUTH_QUERY);
				){
			//READ INPUTS
			String user=null,pass=null;
			if(sc!=null) {
				System.out.println("Enter username::");
				user=sc.nextLine();
				System.out.println("Ente password::");
				pass=sc.nextLine();
			}
			//SET VALUES TO QUERY PARAM
			if(ps!=null) {
				ps.setString(1, user);
				ps.setString(2, pass);
			}
			//SEND AND EXECUTE SQL QUERY
			if(ps!=null) {
				try(ResultSet rs=ps.executeQuery()){
					//PROCESS THE RESULTSET OBJ
					if(rs!=null) {
						rs.next();
						int count=rs.getInt(1);
						if(count==0)
							System.out.println("invalid credentials");
						else
							System.out.println("valid crendentials");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
