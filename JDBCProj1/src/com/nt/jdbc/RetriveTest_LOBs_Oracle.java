package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class RetriveTest_LOBs_Oracle {

	private static final String GET_LOBS_EMPLOYEE="SELECT * FROM EMPLOYEE_LOBS WHERE ENO=?";  
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:thin:1521:xe","system","root");
				PreparedStatement ps=con.prepareStatement(GET_LOBS_EMPLOYEE);
				){
			int no=0;
			if(sc!=null) {
				System.out.println("ENTER EMPLOYEE NUMBER");
				no=sc.nextInt();
			}
			if(ps!=null) {
				//set value to query param
				ps.setInt(1, no);
				
				//execute the sql query
				try(ResultSet rs=ps.executeQuery()){
					if(rs!=null) {
						if(rs.next()) {
							try(InputStream is=rs.getBinaryStream(4);
									Reader reader=rs.getCharacterStream(5);
									OutputStream os=new FileOutputStream("new_photo.jpeg");
									Writer writer=new FileWriter("new_resume.txt");){
								
								//complete the file copy operation
								IOUtils.copy(is,os);
								IOUtils.copy(reader,writer);
								System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));    
								System.out.println("LOBs are retrived to new files");
								
							}//try3
						}//if
						else {
							System.out.println("record not found");
						}
					}//if
				}//try2
			}//if
		}//try1
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
