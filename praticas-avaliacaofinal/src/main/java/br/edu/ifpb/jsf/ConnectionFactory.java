/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.jsf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asheley
 */
public class ConnectionFactory {
     private static Connection con = null;

    private ConnectionFactory() {
    }

    public static Connection getConnection() {
        try {
            if ((con == null) || (con.isClosed())) {
                String usuario = "postgres";
                String senha = "123456";
                String host = "localhost";
                String banco = "galeria";
                Class.forName("com.postgres.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:postgres://" + host + ":5432/" + banco + "?user=" + usuario + "&password=" + senha + "");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }
}
