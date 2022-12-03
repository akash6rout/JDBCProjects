package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTest {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				Statement st=con.createStatement()){
			if(st!=null) {
				//add SQL Queries to the batch
				st.addBatch("INSERT INTO STUDENT VALUES(1158,'RAJA','HYD',90.0)");
				st.addBatch("UPDATE STUDENT SET AVG=AVG+3.0 WHERE SADD='DELHI'");
				st.addBatch("DELETE FROM STUDENT WHERE SNAME LIKE 'b%'");
				//execute the batch
				int result[]=st.executeBatch();
				//process the results
				int total=0;
				for(int i=0;i<result.length;++i) {
					total=total+result[i];
				}
				System.out.println("NO.OF RECORDS THAT ARE EFFECTED::"+total);
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
