/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Random;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author user-BL
 */

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public String userid;
    public String agent = "";
    public String userIP = "";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String token = "";
        String message = "";
        agent = request.getParameter("userAgent").toString();
        userIP = request.getRemoteAddr();
        /*if(user != null && pass != null && !user.equals("") && !pass.equals("")){
            try {
                //creating connection with the database 
                Connection con = DB.connect();
                PreparedStatement ps =con.prepareStatement
                              ("SELECT * FROM user WHERE username = ? AND password = ?;");
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){ 
                    // user exist, generate token
                    token = getToken();
                    message = "successfull";
                } else {
                    // user doesn't exist
                    message = "failed";
                    response.sendError(0, message);
                }
                response.addHeader("token", token);
                response.addHeader("message",message);
                response.flushBuffer();
            } catch( SQLException e) {
                System.out.println(e);
            }
        }*/
        
        if(isUserExist(user, pass)) {
            message = "Login Successful";
            token = getToken();
            response.addHeader("username", user);
            response.addHeader("userid", userid);
            try {
                insertTokenDB(token, user);
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Login Failed : User not exist !";
            response.sendError(0, message);
        }
        response.addHeader("token", token);
        response.addHeader("message",message);
        response.addIntHeader("expiryTime", 30);
        response.flushBuffer();
    }
    
    public String getToken(){
        Random random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        token = token + '#' + agent + '#' + userIP;
        return token;
    }
    
    public void insertTokenDB(String token, String username) throws SQLException{
        try {
            Connection con = DB.connect();
            Timestamp now = new Timestamp(new Date().getTime());
            Statement newPS = con.createStatement();
            newPS.executeUpdate("UPDATE user SET Token='"+token+"' ,createAt='"+now+"' WHERE username ='"+username+"'");
            con.close();
        } catch(Exception e) {
            System.err.println("Got an login exception!");
            System.err.println(e.getMessage());
        }
    }
    
    public boolean isUserExist(String username, String pass) {
        try
        {
          Connection conn = DB.connect();

          Statement st = conn.createStatement();
            // Check if username / email exist
            String query = "SELECT * FROM user WHERE username = '"+username+"' AND password = '"+pass+"'";
            ResultSet result = st.executeQuery(query);
            if (result.next() == true) {
                userid = Integer.toString(result.getInt("id"));
                return true;
            }
            conn.close();
            return false;
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
        return false;
    }
}
