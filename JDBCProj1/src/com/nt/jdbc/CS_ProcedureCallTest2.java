package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_NO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, DESG OUT VARCHAR2 
, SALARY OUT FLOAT 
) AS 
BEGIN
  SELECT ENAME,JOB,SAL INTO NAME,DESG,SALARY FROM EMP WHERE EMPNO=NO;
END P_GET_EMP_DETAILS_BY_NO;*/

public class CS_ProcedureCallTest2 {
	private static final String CALL_PROCEDURE_QUERY="{CALL P_GET_EMP_DETAILS_BY_NO(?,?,?,?)}";

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY);
				){
			int no=0;
			if(sc!=null) {
				//read inputs
				System.out.println("ENTER EMPLOYEE NUMBER::");
				no=sc.nextInt();
				
			}
			
			if(cs!=null) {
				//register OUT param with JDBC types
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.FLOAT);
				//set values to IN params
				cs.setInt(1, no);
				//call the PL/SQL procedure
				cs.execute();
				//get the results from OUT params
				String name=cs.getString(2);
				String desg=cs.getString(3);
				float salary=cs.getFloat(4);
				System.out.println("EMP NAME::"+name);
				System.out.println("EMP DESG::"+desg);
				System.out.println("EMP SALARY::"+salary);
				
				
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
