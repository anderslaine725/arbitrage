/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.core;

import com.arbi.database.DataSet;
import com.arbi.database.QueryParams;
import java.sql.Connection;

public interface IDb {
    Connection getConnection();
    DataSet executeQuery(String query, QueryParams params);
    boolean executeUpdate(String query, QueryParams params);
}
