package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				Statement st=con.createStatement()){
				
				String addrs=null;
				
				if(sc!=null) {
					System.out.println("ENTER STUDENT ADDRESS::");
					addrs=sc.next();
				}
				addrs="'"+addrs+"'";
				//DELETE FROM STUDENT WHERE SADD='HYD'
				String query="DELETE FROM STUDENT WHERE SADD="+addrs;
				System.out.println(query);
				
				//send and execute the sql query
				int count=0;
				if(st!=null)
					count=st.executeUpdate(query);
				//process the result
				if(count==0)
					System.out.println("RECORED NOT FOUND FOR DELETION");
				else
					System.out.println(count+"no.of records are found and deleted");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
