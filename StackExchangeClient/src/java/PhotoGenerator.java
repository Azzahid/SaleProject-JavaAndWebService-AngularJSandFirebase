/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.marketplace.Marketplace_Service;
import com.marketplace.Product;
import com.marketplace.Purchase;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

/**
 *
 * @author Silva
 */
@WebServlet(urlPatterns = {"/PhotoGenerator"})
public class PhotoGenerator extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/MarketplaceWS/marketplace.wsdl")
    private Marketplace_Service service;

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
        //processRequest(request, response);
        String id = request.getParameter("id");
        com.marketplace.Marketplace port = service.getMarketplacePort();
        SerialBlob photo = null;
        String imagetype = null;
        String pilihan = request.getParameter("pilihan");
        try {
            if(Integer.parseInt(pilihan)==1){
                Purchase temp = port.getPhotoPurchase(Integer.parseInt(id));
                photo = new javax.sql.rowset.serial.SerialBlob(temp.getProductPhotourl());
                imagetype = temp.getImageType();
            }else{
                Product temp = port.getPhoto(Integer.parseInt(id));
                photo = new javax.sql.rowset.serial.SerialBlob(temp.getPhotoUrl());
                imagetype = temp.getImageType();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhotoGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            InputStream input = photo.getBinaryStream();
            OutputStream output = response.getOutputStream();
            response.setContentType(imagetype);
            int length = (int) photo.length();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];

            while ((length = input.read(buffer)) != -1) {
              output.write(buffer, 0, length);
            }

            input.close();
            output.flush();
        } catch (SerialException ex) {
            response.getWriter().write("Error : "+ ex);
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

    private Purchase getPhotoPurchase(int id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.marketplace.Marketplace port = service.getMarketplacePort();
        return port.getPhotoPurchase(id);
    }

    private Product getPhoto(int id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.marketplace.Marketplace port = service.getMarketplacePort();
        return port.getPhoto(id);
    }

}
