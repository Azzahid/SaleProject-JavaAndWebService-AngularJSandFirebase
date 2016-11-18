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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Silva
 */
public class SearchServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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
         response.setContentType("text/html");
        String text = request.getParameter("text");
        PrintWriter out = response.getWriter();
        String message = "";
         try
        {
            Connection conn = DB.connect();   
            Statement st = conn.createStatement();
            Integer sum = 0;
            String qrow = "SELECT COUNT(*) AS total FROM user WHERE username LIKE '%"+text+"%'";
            ResultSet re = st.executeQuery(qrow);
            if(re.next()){
                sum = re.getInt("total");
            }else{
                sum = 0;
            }
            String query = "SELECT * FROM user WHERE username LIKE '%"+text+"%'";
            ResultSet result = st.executeQuery(query);
            String hasil = "( ";
            int i = 1;
            while (result.next() == true) {
                if(i!=sum){
                    hasil = hasil + result.getInt("id") +", ";
                }else{
                    hasil = hasil + result.getInt("id")+" ";
                }
                i++;
            }
            hasil = hasil +")";
            conn.close();
            response.addHeader("arrayid", hasil);
            out.write(hasil);
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
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
        response.setContentType("text/html");
        String text = request.getParameter("text");
        PrintWriter out = response.getWriter();
        String message = "";
         try
        {
            Connection conn = DB.connect();   
            Statement st = conn.createStatement();
            Integer sum = 0;
            String qrow = "SELECT COUNT(*) AS total FROM user WHERE username LIKE '%"+text+"%'";
            ResultSet re = st.executeQuery(qrow);
            if(re.next()){
                sum = re.getInt("total");
            }else{
                sum = 0;
            }
            String query = "SELECT * FROM user WHERE username LIKE '%"+text+"%'";
            ResultSet result = st.executeQuery(query);
            String hasil = "( ";
            int i = 1;
            while (result.next() == true) {
                if(i!=sum){
                    hasil = hasil + result.getInt("id") +", ";
                }else{
                    hasil = hasil + result.getInt("id")+" ";
                }
                i++;
            }
            hasil = hasil +")";
            conn.close();
            response.addHeader("arrayid", hasil);
            out.write(hasil);
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
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
