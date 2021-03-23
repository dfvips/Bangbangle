package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.DBManager;

public class Service {
		private String usermsg;
		Boolean result=null;
		public boolean register(String username, String password,String email,String phone) {
			// TODO Auto-generated method stub
			DBManager sq=new DBManager();
			Statement s = sq.getState();
			ResultSet rs = null;
			String sql = "insert into usermsg(username,userpwd,useremail,userphone) values('"
					+ username + "'," + "'" + password + "','" + email
					+ "','" + phone + "')";
			try {
				s.execute(sql);
				result=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
            	return result;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
	
		public boolean login(String username, String password) {
			// TODO Auto-generated method stub
			Boolean result = null;
			DBManager sq=new DBManager();
			Statement s = sq.getState();
			ResultSet rs = null;
			String sql = "select * from usermsg where (username='"+username+"' or useremail='"+username+"' or userphone='"+username+"' ) and userpwd='"+password+"'";
			try {
				rs = s.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			try {
				if (rs.next()) {
					result=true;
					usermsg = rs.getString("userid")+"/"+rs.getString("username")+"/"+rs.getString("useremail")+"/"+rs.getString("userphone");
				
				}else{
					result=false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
            	return result;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
	}
		public String getusermsg(){
			return usermsg;
		}

		public boolean alter(String userid,String password) {
			// TODO Auto-generated method stub
			Boolean result = null;
			DBManager sq=new DBManager();
			Statement s = sq.getState();
			ResultSet rs = null;
			String sql = "update usermsg set userpwd = '"+password+"' where userid="+Integer.valueOf(userid).intValue();
			try {
				s.execute(sql);
				result=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result=false;
			}
            try {
            	return result;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}

		public boolean help(String problem, String name, String phone) {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean find(String username, String email, String phone, String pwd) {
			// TODO Auto-generated method stub
			Boolean result=false;
			DBManager sq1=new DBManager();
		  	Statement s1=sq1.getState();
            ResultSet rs1=null;
            String sql1="select * from  usermsg  where username='"+username+"' and useremail='"+email+"' and userphone='"+phone+"'";
            try {
				rs1=s1.executeQuery(sql1);
				if(rs1.next()) {
							try {
								DBManager sq = new DBManager();
								Statement s = sq.getState();
								String sql2 = "update usermsg set userpwd = '"+pwd+"' where userid="+Integer.valueOf(rs1.getString("userid")).intValue();
								s.execute(sql2);
								result=true;
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								result=false;
							}
					}
            }catch (SQLException e) {
            	// TODO: handle exception
            	result=false;
            }
            try {
            	return result;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
			
		}
}
