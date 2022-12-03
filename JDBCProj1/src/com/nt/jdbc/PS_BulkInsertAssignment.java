package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_BulkInsertAssignment {
	
	private static final String INSERT_MOVIEX_QUERY="insert into moviex values(?,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				PreparedStatement ps=con.prepareStatement(INSERT_MOVIEX_QUERY);
				){
			
			int count =0;
			if(sc!=null) {
				System.out.println("ENTER AUDIAN COUNT::");
				count=sc.nextInt();
			}//if
			
			if(sc!=null && ps!=null) {
				for(int i=1; i<=count; ++i) {
					System.out.println("ENTER"+i+"AUDIAN DETAILS");
					System.out.println("ENTER REGNO::");
					int regno=sc.nextInt();
					System.out.println("ENTER AUDIAN NAME::");
					String name=sc.next();
					System.out.println("ENTER AUDIAN ADDRESS::");
					String addrs=sc.next();
					System.out.println("ENTER AUDIAN AGE");
					int age=sc.nextInt();
					//set the above inputs to precompiled sql query params
					ps.setInt(1, regno);
					ps.setString(2, name);
					ps.setString(3, addrs);
					ps.setInt(4, age);
					//execute the pre-compiled sql query
					int result=ps.executeUpdate();
					//process the result
					if(result==0)
						System.out.println(i+"AUDIAN DETAILS IS NOT INSORTED");
					else
						System.out.println(i+"AUDIAN DETAILS IS INSORTED");
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
