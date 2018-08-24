/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.database;

import com.arbi.core.Global;
import com.arbi.core.IDb;
import com.arbi.core.IUsers;
import com.arbi.core.LoginUser;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Db implements IDb, IUsers {
    class User {
        String username;
        String password;
    }
    
    @Override
    public LoginUser getUserByName(String username) {
        LoginUser user = null;
        Gson gson = new Gson();
        
        try {
            JsonReader reader = new JsonReader(new FileReader(Global.getConfig().getUserPath()));
//            users = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
            User[] users = gson.fromJson(reader, User[].class);
            
            for (User _user: users) {
                if (_user.username.equals(username)) {
                    user = new LoginUser();
                    user.setUsername(username);
                    user.setPassword(_user.password);
                    break;
                }
            }
        } catch (Exception ex) { 
            System.out.println(String.format("load users failed: %s", ex.toString()));
        }
        
        return user;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(jdbcUrl, userName, password);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        return connection;
    }
    

    @Override
    public DataSet executeQuery(String query, QueryParams params) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DataSet dataSet = new DataSet();
        Connection connection = getConnection();

        try {
            stmt = connection.prepareStatement(query);
            if (params != null && !params.isEmpty()) {
                for(int i = 0; i < params.size(); ++i) {
                    int idx = i + 1;
                    Object obj = params.get(i);
                    stmt.setObject(idx, obj);
                }
            }

            rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while(rs.next()) {
                DataRow row = new DataRow();

                for(int i = 1; i <= columnCount; ++i) {
                    Object columnValue = rs.getObject(i);
                    String columnName = rsmd.getColumnLabel(i);
                    row.put(columnName, columnValue);
                }

                dataSet.add(row);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return dataSet;
    }

    @Override
    public boolean executeUpdate(String query, QueryParams params) {
        PreparedStatement stmt = null;
        boolean result = true;
        Connection connection = getConnection();
        
        try {
            stmt = connection.prepareStatement(query);
            if (params != null && !params.isEmpty()) {
                for(int i = 0; i < params.size(); ++i) {
                    int idx = i + 1;
                    Object obj = params.get(i);
                    stmt.setObject(idx, obj);
                }
            }

            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();                
            }
        }
        
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }
    
}
