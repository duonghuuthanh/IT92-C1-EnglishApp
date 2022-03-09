/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.junitdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class Demo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Nap driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Thiet lap ket noi
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/englishdb", "root", "12345678");
        
        // Xu ly truy van
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM question WHERE content like concat('%', ?, '%')");
        stm.setString(1, "is");
//        stm.setString(1, "Noun Phrases");
//        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(); // cursor
        while (rs.next()) {
            String id = rs.getString("id");
            String content = rs.getString("content");
            int cateId = rs.getInt("category_id");
            System.out.printf("%s - %s - %d\n", id, content, cateId);
        }
//        System.out.println("Number: " + row);
        
        // Dong ket noi
        conn.close();
    }
}
