package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				Statement st=con.createStatement()){
			
			String newAddrs=null;
			float newAvg=0.0f;
			int no=0;
			
			if(sc!=null) {
				System.out.println("ENTER STUDENT NUMBER::");
				no=sc.nextInt();
				System.out.println("ENTER NEW ADDRESS FOR THE STUDENT::");
				newAddrs=sc.next();
				System.out.println("ENTER NEW AVG FOR THE STUDENT::");
				newAvg=sc.nextFloat();
			}
			//convert the input value as required for the sql Query
			newAddrs="'"+newAddrs+"'";
			//prepare the sql query
			// UPDATE STUDENT SET SADD='MUMBAI',AVG=56.78 WHERE SNO=132
			String query="UPDATE STUDENT SET SADD="+newAddrs+",AVG="+newAvg+" WHERE SNO="+no;
			System.out.println(query);
			
			//Send and execute the sql query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0) 
			System.out.println("RECORD NOT FOUND FOR UPDATATION");
			else
			System.out.println(count+"no.of record is found and deleted");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
