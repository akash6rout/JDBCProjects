package com.nt.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				Statement st=con.createStatement();
				){
			//read inputs
			String user=null,pass=null;
			if(sc!=null) {
				System.out.println("Enter user name:::");
				user=sc.nextLine();
				System.out.println("Enter Password");
				pass=sc.nextLine();
			}
			//CONVERT THE VALUE AS REQUIRED FOR THE QUERY
			user="'"+user+"'";
			pass="'"+pass+"'";
			//PREPARE THE SQL QUERY
			//SELECT COUNT(*) FROM USERINFO WHERE UNAME='RAJA' AND PWD='RANI';
			String query="SELECT COUNT(*) FROM USERINFO WHERE UNAME="+user+"AND PWD="+pass;
			System.out.println(query);
			
			if(st!=null) {
				try(ResultSet rs=st.executeQuery(query)){
					//PROCESS THE RESULTSET OBJ
					if(rs!=null) {
						rs.next();
						int count=rs.getInt(1);
						if(count==0)
							System.out.println("InValid Credentials");
						else
							System.out.println("Valid Credentials");
					}
				}
			}
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
