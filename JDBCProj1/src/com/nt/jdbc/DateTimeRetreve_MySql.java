package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DateTimeRetreve_MySql {

	private static final String GET_EMP_DATES_QUERY="SELECT * FROM EMPLOYEE";
	
	public static void main(String args[]) {
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj916db","root","123456789");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(GET_EMP_DATES_QUERY);
				){
			if(rs!=null) {
				while(rs.next()){
					int no=rs.getInt(1);
					String name=rs.getString(2);
					java.sql.Date sqdob=rs.getDate(3);
					java.sql.Time sqtob=rs.getTime(4);
					java.sql.Timestamp sqdoj=rs.getTimestamp(5);
					//convert java.sql.Date class obj requied String pattern date Value
					SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
					String sdob=sdf.format(sqdob);
					System.out.println(no+" "+name+" "+sdob+" "+sqtob+" "+sqdoj);
				}//while
			}//if
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
