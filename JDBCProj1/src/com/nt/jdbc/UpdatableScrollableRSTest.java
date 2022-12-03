package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableScrollableRSTest {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracl:thin:@localhost:1521:xe","system","root");
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=st.executeQuery("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
				){
			System.out.println("Records (top-bottom)");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}
			System.out.println("insert operation");
			/*rs.moveToInsertRow();
			rs.updateInt(1, 4445);
			rs.updateString(2, "rajesh");
			rs.updateString(3,"clerk");
			rs.updateDouble(4,90000.0);
			rs.insertRow();
			System.out.println("RECORD INSERTED");*/
			
			/*System.out.println("Update operation");
			rs.absolute(4);
			rs.updateString(3,"Manager");
			rs.updateRow();
			System.out.println("Recored UPDATED");*/
			
			rs.absolute(5);
			rs.deleteRow();
			System.out.println("Recored deleted");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
