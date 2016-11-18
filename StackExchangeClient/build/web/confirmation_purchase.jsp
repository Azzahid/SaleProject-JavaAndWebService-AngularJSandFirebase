<%-- 
    Document   : confirmation_purchase
    Created on : Nov 7, 2016, 8:59:31 PM
    Author     : user-BL
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Timestamp now = new Timestamp(System.currentTimeMillis());
        int user_id = 0;
        String error = "";
        user_id = 0;
       String username = "";
       String consignee = "";
       String address = "";
       String postalcode = "";
       String phonenumber = "";
       com.marketplace.Product product = null;
        int product_id = 0;
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

                
               
                if(connection.getResponseMessage().contains("invalid")) {
                    error = connection.getResponseMessage();
                    username = "error";
                }
                else {
                    username = connection.getHeaderField("username");
                    consignee = connection.getHeaderField("fullname");
                    address = connection.getHeaderField("address");
                    postalcode = connection.getHeaderField("postalcode");
                    phonenumber = connection.getHeaderField("phonenumber");
                    user_id = Integer.parseInt(connection.getHeaderField("user_id"));
                    product_id = Integer.parseInt(request.getParameter("id_product"));
                }

                
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
            }
        }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Purchase</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/header.css">
    </head>
    <body class="body-center helvetica">
        <div>
            <jsp:include page="header.jsp" />  
            <div class = "border-bottom ">
                <h2>Please confirm your purchase</h2>
            </div>

            <form method="post" id="purchase_form" action="purchases.jsp">
                <span id="product_price" value="<%out.print(product.getPrice());%>"></span>
                <input type="hidden" name="id_product" value="<%out.print(product_id);%>">
                <input type="hidden" name="id_active" value="<%out.print(user_id);%>">
                <p>Product : <%out.print(product.getNamaProduk());%></p>
                <p>Price : IDR <%out.print(product.getPrice());%></p>
                <p>Quantity :
                    <input type="text" name="quantity" value="1" onkeyup="updateTotalPrice()" id="quantity">
                    pcs
                </p>
                <p>Total Price : IDR <span id="total_price"><%out.print(product.getPrice());%></span></p>
                <p>Delivery to :</p>
                <div>
                    <label for="consignee">Consignee</label><br />
                    <input type="text" name="consignee" id="consignee" value="<%out.print(consignee);%>" class="input-text">
                </div>
                <div>
                    <label for="full_address">Full Address</label><br />
                    <textarea name="full_address" rows="5" cols="50" id="full_address" class="input-textarea    "><%out.print(address);%></textarea>
                </div>
                <div>
                    <label for="postal_code">Postal Code</label><br />
                    <input type="text" name="postal_code" id="postal_code" value="<%out.print(postalcode);%>" class="input-text">
                </div>
                <div>
                    <label for="phone_number">Phone Number</label><br />
                    <input type="text" name="phone_number" id="phone_number" value="<%out.print(phonenumber);%>" class="input-text">
                </div>
                <div>
                    <label for="credit_card">12 Digits Credit Card Number</label><br />
                    <input type="text" name="credit_card" id="credit_card" class="input-text">
                </div>
                <div>
                    <label for="card_verification">3 Digits Card Verification Value</label><br />
                    <input type="text" name="card_verification" id="card_verification" class="input-text">
                </div>
                <div>
                    <a href="catalog.jsp" class="cancel-button float-right">CANCEL</a>

                    <input type="button" value="CONFIRM" onclick="getConfirmation();" class="button float-right">
                </div>
            </form>
        </div>
    </body>

    <script type="text/javascript" src="js/confirmation_purchase.js"></script>
</html>
