/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author user
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        String full_name = request.getParameter("full_name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String full_address = request.getParameter("full_address");
        String postal_code = request.getParameter("postal_code");
        String phone_number = request.getParameter("phone_number");
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        // register new user
        String message = "";
        String token = "";
        Timestamp expiryTime = new Timestamp(System.currentTimeMillis());
        long duration = ((1 * 60) + 1) * 1000;
        expiryTime.setTime(expiryTime.getTime() + duration);

        // check if user already exists
        if(isUserExist(username, email)) {
            message = "Registration failed: User already exists";
            response.sendError(0, message);
        }
        else {
            token = register(full_name, username, email, pass, full_address, postal_code, phone_number);
            if(token != null) {
                message = "Registration success"+token;
            }
            else {  // registration failed
                message = "Registration failed";
                response.sendError(0, message);
            }
        }
        
        response.addHeader("message", message);
        response.addHeader("token", token);
        response.addIntHeader("expiryTime", 3);
        response.flushBuffer();
        
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RegisterServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
//            out.println("<p>Message: " + message + "</p>");
//            out.println("<p>Message: " + full_name + "</p>");
//            out.println("<p>Message: " + username + "</p>");
//            out.println("<p>Message: " + email + "</p>");
//            out.println("<p>Message: " + pass + "</p>");
//            out.println("<p>Message: " + full_address + "</p>");
//            out.println("<p>Message: " + postal_code + "</p>");
//            out.println("<p>Message: " + phone_number + "</p>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }
    
    public boolean isUserExist(String username, String email) {
        try
        {
          Connection conn = DB.connect();

          Statement st = conn.createStatement();
            // Check if username / email exist
            String query = "SELECT * FROM user WHERE username = '"+username+"' OR email = '"+email+"'";
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
    
    public String register(String full_name, String username, String email, String pass, String full_address, String postal_code, String phone_number) {
        String token = null;
        try
        {
          Connection conn = DB.connect();

          Statement st = conn.createStatement();

          LoginServlet l = new LoginServlet();
          token = l.getToken();
          Timestamp now = new Timestamp(new java.util.Date().getTime());
          st.executeUpdate("INSERT INTO user (fullname, username, email, password, address, postalcode, phonenumber, Token, createAt) "
              +"VALUES ('"+full_name+"', '"+username+"', '"+email+"', '"+pass+"', '"+full_address+"', '"+postal_code+"', '"+phone_number+"', '"+token+"', '"+now+"')");
          conn.close();
        }
        catch (Exception e)
        {
          token = null;
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
        return token;
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
