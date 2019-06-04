/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.*;

/**
 *
 * @author fojomars
 */
public class Mostrar {
    public static Connection getConnection(){
        Connection cn=null;
        try {
            cn=DriverManager.getConnection("jdbc:sqlite:snake.db");
        } catch (Exception e) {
            System.out.println(String.valueOf(e));
        }
        return cn;
    }
    
    
    public static ResultSet getTabla(String consulta) throws SQLException {
        Connection cn=getConnection();
        Statement st;
        ResultSet datos=null;
        try{
            st=cn.createStatement();
            datos=st.executeQuery(consulta);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    return datos;
    }
}
