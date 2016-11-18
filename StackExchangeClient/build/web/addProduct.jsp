<%-- 
    Document   : addProduct
    Created on : Nov 7, 2016, 8:53:17 PM
    Author     : user-BL
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.io.InputStream"%>
<%@page import="org.apache.commons.fileupload.FileItemStream"%>
<%@page import="org.apache.commons.fileupload.FileItemIterator"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Timestamp now = new Timestamp(System.currentTimeMillis());
    int user_id = 0;
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
            String url = "http://localhost:8082/IdentityServices/TokenServlet";
            URL iurl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)iurl.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Send POST output.
            connection.setRequestMethod("POST");
            java.io.DataOutputStream printout = new java.io.DataOutputStream(connection.getOutputStream ());
            String token = (String)session.getAttribute("token");
        //    out.println(token);
            String content = "token=" + token;
            printout.writeBytes (content);
            printout.flush (); 
            printout.close ();  

            // retrieve response from IS
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(
                (java.io.InputStream) connection.getContent()));
            String line;

            String error = "";
            user_id = 0;
            if(connection.getResponseMessage().contains("invalid")) {
                error = connection.getResponseMessage();
                response.sendRedirect("login.jsp");
            }
            else {
                user_id = Integer.parseInt(connection.getHeaderField("user_id"));
            }

            if("POST".equalsIgnoreCase(request.getMethod())) {
                ServletFileUpload upload = new ServletFileUpload();
                String pname="",pdesc="",pprice = "";
                String imagename="",imagetype="";
                byte[] imagedata = null;
                InputStream filecontent = null;

                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : items){
                    if (item.isFormField()) {
                        if(item.getFieldName().equals("productName")){
                            pname = item.getString();
                        } else if(item.getFieldName().equals("productDescription")){
                            pdesc = item.getString();
                        } else if(item.getFieldName().equals("productPrice")){
                            pprice = item.getString();
                        }
                    } else {
                        // Process form file field (input type="file").
                        filecontent = item.getInputStream();
                        out.println(filecontent);
                        imagename = FilenameUtils.getName(item.getName());
                        imagetype = FilenameUtils.getName(item.getContentType());
                        imagedata = IOUtils.toByteArray(filecontent);
                    }
                }

                try {
                    com.marketplace.Marketplace_Service service = new com.marketplace.Marketplace_Service();
                    com.marketplace.Marketplace port = service.getMarketplacePort();
                     // TODO initialize WS operation arguments here
                    java.lang.String productname = pname;
                    java.lang.String description = pdesc;
                    java.lang.String price = pprice;
                    java.lang.String imageblob = Base64.getEncoder().encodeToString(imagedata);
                    int userid = user_id;
                    java.lang.String imageType = imagetype;
                    java.lang.String imageName = imagename;
                    // TODO process result here
                    java.lang.Boolean result = port.addProduct(productname, description, price, imageblob, userid, imageType, imageName);
                    out.println("Result = "+result);
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                response.sendRedirect("your_products.jsp");
            }
        }
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
        <link rel="stylesheet" type ="text/css" href="css/style.css">
        <link rel="stylesheet" type ="text/css" href="css/header.css">
        <script src="catalog.js"></script>
    </head>
    <body class="body-center helvetica">
        <jsp:include page="header.jsp" />    
        <div class = "border-bottom ">
            <h2>Please add your product here</h2>
        </div>
        <form method="POST" name="addForm" id="addForm" onsubmit="return validateform()" action= "addProduct.jsp" enctype="multipart/form-data">	
            <span class="font-small">Name</span><br><input type="text" id="productName" name="productName" class="input-text" /><br>
            <span class="font-small">Description (max 200 chars)</span><br><textarea id="productDescription" name="productDescription" rows="4" cols="50" class="input-textarea"></textarea><br>
            <span class="font-small">Price (IDR)</span><br><input type="text" id="productPrice" name="productPrice" class="input-text" /><br>
            <span class="font-small">Photo</span><br><input type="file" id="fileToUpload" name="fileToUpload" /><br><br>
            <a href="catalog.jsp" class="cancel-button float-right">CANCEL</a>

            <input type="submit" value="ADD" name="addsubmit" class="button float-right" >
        </form>
        <script src = "js/addProduct.js"></script>
    </body>
</html>

