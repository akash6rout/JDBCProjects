package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				Statement st=con.createStatement();
				){
				String name=null,addrs=null;
				float avg=0.0f;
				int no=0;
				//read inputs from enduser
				if(sc!=null)
				{
					System.out.println("ENTER STUDENT NUMBER::");
					no=sc.nextInt();
					System.out.println("ENTER STUDENT NAME::");
					name=sc.next();
					System.out.println("ENTER STUDENT ADDRESS::");
					addrs=sc.next();
					System.out.println("ENTER STUDENT MARKS AVG::");
					avg=sc.nextFloat();
					
					}
		//CONVERT INPUT VALUES AS REQUIRED FOR THE SQL QUERY
		name="'"+name+"'";
		addrs="'"+addrs+"'";
		
		//prepare sql query
		//INSERT INTO STUDENT VALUES(101,'RAJA','HYD',90.90)
		String query="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")";
		System.out.println(query);
		
		//send and execute the sql query
		int count=0;
		if(st!=null)
			count=st.executeUpdate(query);
		//process the result
		if(count==0)
			System.out.println("record not inserted");
		else
			System.out.println("record inserted");
	}//try
	catch(SQLException se) {
		se.printStackTrace();
	}
	catch(Exception e) {
		e.printStackTrace();
	}

}
}

