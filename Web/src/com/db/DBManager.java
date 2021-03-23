/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ÃÎØ¼ËæÐÄ·É
 */
public class DBManager {
    private Statement state;
    private java.sql.Connection con;
    public DBManager() {
        try {
          
           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;DatabaseName=BangBangle";
            String name="aaa";
            String mima="aaa";
            con=DriverManager.getConnection(url, name, mima);
            state = con.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void close() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Statement getState() {
        return state;
    }

    public void setState(Statement state) {
        this.state = state;
    }
 
}
