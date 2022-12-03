package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferInfoFromOracleToMySQL {
	private static final String GET_ALL_STUDENTS_ORACLE="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	private static final String INSERT_STUDENT_MYSQL="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {
		
		try(Connection oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				Connection mySqlCon=DriverManager.getConnection("jdbc:mysql:///NTAJ916DB","root","123456789");
				Statement st=oraCon.createStatement();
				PreparedStatement ps=mySqlCon.prepareStatement(INSERT_STUDENT_MYSQL);
				){
			//EXECUTE THE SELECT SQL QUERY IN ORACLE DB S/W
			try(ResultSet rs=st.executeQuery(GET_ALL_STUDENTS_ORACLE)){
			//PROCESS THE RESULTSET AND INSERT THOSE RECORDS TO MYSQL DB TABLE
			if(rs!=null && ps!=null) {
				while(rs.next()) {
					//GET EACH RECORDS FROM ORACLE DB TABLE "STUDENT"
					int no=rs.getInt(1);
					String name=rs.getString(2);
					String addrs=rs.getString(3);
					float avg=rs.getFloat(4);
					//SET THE ABOVE TO INSERT SQL QUERY PARAMS(?) REFERED BY PREPARED STATEMENT OBJ
					ps.setInt(1, no);
					ps.setString(2,name);
					ps.setString(3, addrs);
					ps.setFloat(4, avg);
					//execute the sql query
					ps.executeUpdate();
				}//while
				System.out.println("THE STUDENT DB TABLE RECORDS OF ORACLE DB S/W COPIED TO MYSQL DB TABLE STUDENT");
			}//IF
			}//TRY2
			
		}//TRY1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception s) {
			s.printStackTrace();
		}

	}//MAIN

}//CLASS
