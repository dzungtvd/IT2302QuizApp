/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.services;

import com.tvd.pojo.Category;
import com.tvd.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class BaseServices<T> {
    public abstract PreparedStatement getStatement(Connection conn) throws SQLException;
    public abstract List<T> getResults(ResultSet rs) throws SQLException; 
    
    public List<T> list() throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = this.getStatement(conn);
        
        return this.getResults(stm.executeQuery());
        
//        Statement atm = conn.createStatement();
//        ResultSet rs = atm.executeQuery("SELECT * FROM category");
//
//        List<Category> cates = new ArrayList<>();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//
//            Category c = new Category(id, name);
//            cates.add(c);
//        }
//        return cates;
    }
}
