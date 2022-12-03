package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE PROCEDURE P_GET_EMPS_BY_DESG 
(
  DESG1 IN VARCHAR2 
, DESG2 IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
    OPEN DETAILS FOR
  SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN (DESG1,DESG2)ORDER BY JOB;
END P_GET_EMPS_BY_DESG;*/

public class CS_ProcedureCallTest3 {
	
	private static final String CALL_PROCEDURE_QUERY="{CALL P_GET_EMPS_BY_DESG(?,?,?)}";

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY);
				){
			String desg1=null,desg2=null;
			if(sc!=null) {
				//read inputs
				System.out.println("ENTER EMPLOYEE DESG1::");
				desg1=sc.next();
				System.out.println("ENTER EMPLOYEE DESG2::");
				desg2=sc.next();
				
			}
			if(cs!=null) {
				//register OUT params with JDBC type
				cs.registerOutParameter(3, OracleTypes.CURSOR);
				//SET VALUES TO IN PARAMS
				cs.setString(1, desg1);
				cs.setString(2, desg2);
				//call the PL/SQL procedure
				cs.execute();
				//get the results from OUT params
				try(ResultSet rs=(ResultSet)cs.getObject(3)){
					//process the resultset
					if(rs!=null) {
						System.out.println("EMPLOYEE DETAILS HAVING::"+desg1+" "+desg2);
						while(rs.next()) {
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));;
						}
					}
				}
				
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
