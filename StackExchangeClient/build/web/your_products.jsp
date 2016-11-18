<%-- 
    Document   : your_products
    Created on : Nov 7, 2016, 9:14:36 PM
    Author     : user-BL
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="com.marketplace.Product"%>
<%@page import="java.text.SimpleDateFormat"%>
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
            String ds = request.getParameter("d");


            if("GET".equalsIgnoreCase(request.getMethod()) && ds != null) {
                int d = Integer.parseInt(ds);
    //            //out.println("d: "+d);
                try {
                    com.marketplace.Marketplace_Service service = new com.marketplace.Marketplace_Service();
                    com.marketplace.Marketplace port = service.getMarketplacePort();
                     // TODO initialize WS operation arguments here
                    int productId = d;
                    // TODO process result here
                    java.lang.Boolean result = port.deleteProduct(productId);
    //                //out.println("Result = "+result);
                } catch (Exception ex) {
    //                 out.println("ex = "+ex);
                }
            }
            if("POST".equalsIgnoreCase(request.getMethod())) {
                try {
                    com.marketplace.Marketplace_Service service = new com.marketplace.Marketplace_Service();
                    com.marketplace.Marketplace port = service.getMarketplacePort();
                     // TODO initialize WS operation arguments here
                    java.lang.String productName = request.getParameter("productName");
//                    //out.println(productName);
                    java.lang.String productPrice = request.getParameter("productPrice");
//                    //out.println(productPrice);
                    java.lang.String productDescription = request.getParameter("productDescription");
//                    //out.println(productDescription);
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    //out.println(productId);
                    // TODO process result here
                    java.lang.Boolean result = port.editProduct(productName, productPrice, productDescription, productId);
//                    //out.println("Result = "+result);
                } catch (Exception ex) {
                    out.println("ex = "+ex);
                }
            }
        }
    }        
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Your Products</title>
        <link rel="stylesheet" type="text/css" href="css/products.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/header.css">
    </head>
    <body class="body-center helvetica">
        <jsp:include page="header.jsp" />
        <span id="id_active" value="<%out.print(user_id);%>"></span>
        <div class = "border-bottom ">
            <h2>What are you going to sell today?</h2>
        </div>
    
        
        
        <%
        java.util.List<com.marketplace.Product> result = null;
        com.marketplace.Marketplace port = null;
        try {
            com.marketplace.Marketplace_Service service = new com.marketplace.Marketplace_Service();
            port = service.getMarketplacePort();
             // TODO initialize WS operation arguments here
            int userId = user_id;
            // TODO process result here
            result = port.getYourProduct(userId);
//            out.println("Result = "+result);
        } catch (Exception ex) {
//            out.println("ex = "+ex);
        }
        if(result != null && result.size()>0){
                SimpleDateFormat ft = new SimpleDateFormat("E, dd M yyyy");
                SimpleDateFormat tt = new SimpleDateFormat("HH.mm");
                for(int i =0; i<result.size();i++){
                    Product temp = result.get(i);
                    Date datetemp = temp.getCreatedAt().toGregorianCalendar().getTime();
                    int totallike = port.getLike(temp.getPId());
                    int status = port.getLikeStatus(temp.getPId(), 7);
                    int totalpurchase = port.getTotalPurchase(temp.getPId());
                    out.print("<div class = 'product'>");
                            out.print("<span class='product-date'>"+ft.format(datetemp)+"</span> <br />");
                            out.print("<span class='product-time'>added this on "+tt.format(datetemp)+"</span><hr />");
                            out.print("<img src='/StackExchangeClient/PhotoGenerator?id="+temp.getPId()+"&pilihan=2'"+"' alt='product-image' width='100px' height='100px'>");
                            out.print("<div class = 'product-center-description'>"
                                        + "<span class='product-name'>"+temp.getNamaProduk()+"</span><br />"
                                        + "<span class='product-price'>IDR "+NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(temp.getPrice()))+"</span><br />"
                                        + "<span class='product-desc'>"+temp.getDescription()+"</span><br />"
                                    + "</div>");
                            out.print("<div class='product-right-description'>"
                                    + "<div class = 'margin-top'>"
                                    +   "<span class='product-desc' id = '"+temp.getPId()+"-num'>"+"</span>"
                                    +   "<span class='product-desc'>"+totallike+" Likes</span>"
                                    +   "<div class='product-desc'>"+totalpurchase+" purchases</div>"
                                    + "</div>"
                                    + "<div class = 'margin-top'>"
                                    +       "<span class = 'edit' onclick='edit_item(this.id);' id='"+temp.getPId()+"'>EDIT</span>"
                                    +       "<span class = 'delete' onclick='delete_item(this.id);' id='"+temp.getPId()+"'>DELETE</span>"
                                    + "</div>");
                              out.print("</div>");
                            out.print("<hr class='full' />");
                          
                }
            }else{
                out.print("Product Not Found");
            }
    %>
    
    
    </body>
    <script type="text/javascript" src="js/your_products.js"></script>
</html>
