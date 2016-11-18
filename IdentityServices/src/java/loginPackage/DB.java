/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginPackage;

import java.sql.*;
import java.lang.*;

/**
 *
 * @author user
 */
public class DB {
    private static String username="root";
    private static String password="";
    /* Adjust the above two as per the username
    * password combination of your MySql databse */

    public static Connection connect() {
       try
        {
            Class.forName("com.mysql.jdbc.Driver");  
            String url="jdbc:mysql://localhost:3306/t2_akun";
            Connection con = DriverManager.getConnection(url,username,password);
            return con;
        }
        catch(Exception e)
        {             
             System.out.println(e);
             return null;
        } 
    }
}