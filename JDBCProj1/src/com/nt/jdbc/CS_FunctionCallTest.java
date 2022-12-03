package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION FX_GET_STUD_DETAILS_BY_NO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, AVERAGE OUT FLOAT 
) RETURN VARCHAR2 AS 
    ADDRS VARCHAR2(20);
BEGIN
    SELECT SNAME,SADD,AVG INTO NAME,ADDRS,AVERAGE FROM STUDENT WHERE SNO=NO;
  RETURN ADDRS;
END FX_GET_STUD_DETAILS_BY_NO;*/

public class CS_FunctionCallTest {
	
	private static final String CALL_FUNCTION_QUERY="{?=call FX_GET_STUD_DETAILS_BY_NO(?,?,?)}";

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				CallableStatement cs=con.prepareCall(CALL_FUNCTION_QUERY);
				){
			int no=0;
			if(sc!=null) {
				System.out.println("ENTER STUDENT NUMBER::");
				no=sc.nextInt();
			}
			if(cs!=null) {
				//register RETURN,OUT params
				cs.registerOutParameter(1, Types.VARCHAR);//RETURN PARAM
				cs.registerOutParameter(3, Types.VARCHAR);//out param
				cs.registerOutParameter(4, Types.FLOAT);
				//set values to IN params
				cs.setInt(2, no);
				//call PL/SQL function 
				cs.execute();
				//get results from OUT , return params
				String addrs=cs.getString(1);
				String name=cs.getString(3);
				float avg=cs.getFloat(4);
				System.out.println("STUDENT NAME::"+name);//out param
				System.out.println("STUDENT ADDRESS::"+addrs);//return param
				System.out.println("STUDENT AVG::"+avg);//out param
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
