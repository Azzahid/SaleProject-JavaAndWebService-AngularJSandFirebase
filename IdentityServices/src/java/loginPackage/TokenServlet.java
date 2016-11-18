/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author YUKI
 */
@WebServlet(name = "TokenServlet", urlPatterns = {"/TokenServlet"})
public class TokenServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TokenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TokenServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        response.setContentType("text/html");
        String token = request.getParameter("token");
        PrintWriter out = response.getWriter();
        String message = "";
        if(isTokenValid(token)) {
            message = "Token valid";
            response.addHeader("username", getUsername(token));
            response.addIntHeader("user_id", getUserId(token));
            response.addHeader("fullname", getFullname(token));
            response.addHeader("address", getAddress(token));
            response.addHeader("postalcode", getPostalCode(token));
            response.addHeader("phonenumber", getPhoneNumber(token));
        } else {
            message = "Token invalid";
            response.sendError(0, message);
        }
        
        response.addHeader("message",message);
        response.flushBuffer();
    }
    
    public int getUserId(String token) {
        int user_id = 0;
        try
        {
          Connection conn = DB.connect();

          Statement st = conn.createStatement();
            // Check if token valid
            String query = "SELECT * FROM user WHERE token = '"+token+"'";
            ResultSet result = st.executeQuery(query);
            if (result.next() == true) {
                user_id = result.getInt("id");
            }
            conn.close();
            return user_id;
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
        return user_id;
    }
    
    public String getUsername(String token) {
        String username = "";
        try
        {
          Connection conn = DB.connect();

          Statement st = conn.createStatement();
            // Check if token valid
            String query = "SELECT * FROM user WHERE token = '"+token+"'";
            ResultSet result = st.executeQuery(query);
            if (result.next() == true) {
                username = result.getString("username");
            }
            conn.close();
            return username;
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
        return username;
    }
    
    public String getFullname(String token) {
        String username = "";
        try
        {
          Connection conn = DB.connect();

          Statement st = conn.createStatement();
            // Check if token valid
            String query = "SELECT * FROM user WHERE token = '"+token+"'";
            ResultSet result = st.executeQuery(query);
            if (result.next() == true) {
                username = result.getString("fullname");
            }
            conn.close();
            return username;
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
        return username;
    }
    
    public String getAddress(String token) {
        String username = "";
        try
        {
          Connection conn = DB.connect();

          Statement st = conn.createStatement();
            // Check if token valid
            String query = "SELECT * FROM user WHERE token = '"+token+"'";
            ResultSet result = st.executeQuery(query);
            if (result.next() == true) {
                username = result.getString("address");
            }
            conn.close();
            return username;
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
        return username;
    }
    
    public String getPostalCode(String token) {
        String username = "";
        try
        {
          Connection conn = DB.connect();

          Statement st = conn.createStatement();
            // Check if token valid
            String query = "SELECT * FROM user WHERE token = '"+token+"'";
            ResultSet result = st.executeQuery(query);
            if (result.next() == true) {
                username = result.getString("postalcode");
            }
            conn.close();
            return username;
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
        return username;
    }
    
    public String getPhoneNumber(String token) {
        String username = "";
        try
        {
          Connection conn = DB.connect();

          Statement st = conn.createStatement();
            // Check if token valid
            String query = "SELECT * FROM user WHERE token = '"+token+"'";
            ResultSet result = st.executeQuery(query);
            if (result.next() == true) {
                username = result.getString("phonenumber");
            }
            conn.close();
            return username;
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
        return username;
    }
    
    public boolean isTokenValid(String token) {
        try
        {
          Connection conn = DB.connect();

          Statement st = conn.createStatement();
            // Check if token valid
            String query = "SELECT * FROM user WHERE token = '"+token+"'";
            ResultSet result = st.executeQuery(query);
            if (result.next() == true) {
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
