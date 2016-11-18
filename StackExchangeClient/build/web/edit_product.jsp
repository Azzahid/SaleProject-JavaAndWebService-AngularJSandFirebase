<%-- 
    Document   : edit_product
    Created on : Nov 7, 2016, 9:06:18 PM
    Author     : user-BL
--%>

<%@page import="java.sql.Timestamp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Timestamp now = new Timestamp(System.currentTimeMillis());
    int user_id = 0;
    int product_id = 0;
     com.marketplace.Product product = null;
    if(session.getAttribute("expire") == null) {
        response.sendRedirect("login.jsp");
    }
    else {
        Timestamp expire = (Timestamp)session.getAttribute("expire");
        if(expire.before(now)) {
            session.setAttribute("userid", null);
            session.setAttribute("username", null);
            session.setAttribute("token", null);
            session.setAttribute("expire", null);
            session.invalidate();
            response.sendRedirect("login.jsp");
        } 
        else { 

             product_id = Integer.parseInt(request.getParameter("id_product"));

            try {
                com.marketplace.Marketplace_Service service = new com.marketplace.Marketplace_Service();
                com.marketplace.Marketplace port = service.getMarketplacePort();
                 // TODO initialize WS operation arguments here
                int id = product_id;
                // TODO process result here
                 product = port.getPhoto(id);
            } catch (Exception ex) {
                out.println("Ex = "+ex);
            }
            if("POST".equalsIgnoreCase(request.getMethod())) {
                out.println("POST");
            }
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Product</title>
        <link rel="stylesheet" type ="text/css" href="css/style.css">
        <link rel="stylesheet" type ="text/css" href="css/header.css">
        <script src="catalog.js"></script>
    </head>
    <body class="body-center helvetica">
        <jsp:include page="header.jsp" />
        <div class = "border-bottom ">
            <h2>Please update your product here</h2>
        </div>

        <form method="POST" name="editForm" id="editForm" action= "your_products.jsp">	
            <input type="hidden" id="productId" name="productId"  value="<%out.print(product_id);%>"></input>
            <span class="font-small">Name</span><br><input type="text" id="productName" name="productName" class="input-text" value="<%out.print(product.getNamaProduk());%>" /><br>
            <span class="font-small">Description (max 200 chars)</span><br><textarea id="productDescription" name="productDescription"  rows="4" cols="50" class="input-textarea"><%out.print(product.getDescription());%></textarea><br>
            <span class="font-small">Price (IDR)</span><br><input type="text" id="productPrice" name="productPrice" class="input-text" value="<%out.print(product.getPrice());%>"/><br>
            <span class="font-small">Photo</span><br><input type="file" id="fileToUpload" name="fileToUpload" style="color:transparent; width:90px"/><%out.print(product.getNamaProduk()+"."+product.getImageType());%><label id="fileLabel"></label><br><br>
            <a href="catalog.jsp" class="cancel-button float-right">CANCEL</a>

            <input type="submit" value="UPDATE" name="update" class="button float-right" >
        </form>
        <script src="js/editProduct.js"></script>		
    </body>
</html>
