package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int no=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("enter employee number");
				no=sc.nextInt();
			}
			//load jdbc driver class (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			//create Statement ojb
				if(con!=null)
				st=con.createStatement();
				//prepare SQL Query
				//SELECT EMPNO,ENAME,JOB,SAL,SEPTNO FROM EMP WHERE EMPNO=7499
				String query="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE EMPNO="+no;
				System.out.println(query);
				//send and execute the sql query
				if(st!=null)
				rs=st.executeQuery(query);
				//process the ResultSet obj(1 or 0 records)
				if(rs!=null)
				{
				if(rs.next())
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getDouble(4)+" "+rs.getInt(5));
				else
				System.out.println("Recored not found");
				}//if
			}
			catch(SQLException se){//for known exception
			se.printStackTrace();
			}
			catch(ClassNotFoundException cnf){//for known exception
			cnf.printStackTrace();
			}
			catch(Exception e){//for unknown exception
			e.printStackTrace();
			}
			finally{
			//close jdbc objs
			try{
			if(rs!=null)
				rs.close();
				}
				catch(SQLException se){
				se.printStackTrace();
				}
				try{
			if(st!=null)
				st.close();
				}
				catch(SQLException se){
				se.printStackTrace();
				}
			try{
			if(con!=null)
				con.close();
				}
				catch(SQLException se){
				se.printStackTrace();
				}
				try{
			if(sc!=null)
				sc.close();
				}
				catch(Exception e){
				e.printStackTrace();
				}
		}

	}

}
