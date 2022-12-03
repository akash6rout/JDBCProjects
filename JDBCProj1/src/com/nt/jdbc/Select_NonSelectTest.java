package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Select_NonSelectTest {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				Statement st=con.createStatement();
				){
				//read input values from enduser
				String query=null;
				if(sc!=null) {
					System.out.println("ENTER SQL QUERY(select or non-select)::");
					query=sc.nextLine();
				}
				//send and execute the sql query
				boolean flag=false;
				if(st!=null)
					flag=st.execute(query);
				//process the ResultSet
				if(flag) {
					System.out.println("SELECT SQL QUERY IS EXECUTED");
					try(ResultSet rs=st.getResultSet()){
						//process the ResultSet obj
						if(rs!=null) {
							while(rs.next()) {
								System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
							}//while
						}//if
					}//try
				}//if
				else {
					System.out.println("NON-SELECT SQL QUERY IS EXECUTED");
					int count=st.getUpdateCount();
					System.out.println("NO. OF RECORED THAT ARE AFFECTED::"+count);
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
