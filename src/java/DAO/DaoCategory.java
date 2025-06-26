/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sontu
 */
public class DaoCategory {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Category> getAllCategory() {
        String sql = "select *from Category";
        List<Category> l = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int categoryID = rs.getInt(1);
                String categoryName = rs.getString(2);
                Category c = new Category(categoryName, categoryName);
                l.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public int getCategoryIDbyName(String categoryName) {
        String sql = "select Category.CategoryID from Category where Category.CategoryName= ?;";
        int categoryID = 0;
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setString(1, categoryName);
            rs = ps.executeQuery();
            while (rs.next()) {
                categoryID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryID;
    }

    public static void main(String[] args) {
    }
}
