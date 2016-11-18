<%@page import="java.sql.Timestamp"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.marketplace.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalog</title>
        <link rel="stylesheet" type ="text/css" href="css/catalog.css">
        <link rel="stylesheet" type ="text/css" href="css/style.css">
        <link rel="stylesheet" type ="text/css" href="css/header.css">
        <link rel="stylesheet" type ="text/css" href="css/products.css">
    </head>
    <body class="body-center helvetica">
        <jsp:include page="header.jsp" />
        <!-- bar question -->
        <div class = "border-bottom ">
                <h2>What are you going to buy today ?</h2>
        </div>
        <!-- search form -->
        <form action="" method="get">
            <input type="hidden" name="id_active"  />
            <div class="overflow margin10">
                <input type ="text" id="searchbar" name="search" 
                placeholder="Search catalog ...">
                <button type = "submit" id="gobutton" class="bluebox">GO</button>
            </div>
            <div class="overflow">
                <div class="floatl">by :</div>
                <div class="floatl">
                    <input type ="radio" name = "option" value=0 checked>
                    <label for="option"><span><span></span></span>product</label>
                    <br>
                    <input type ="radio" name = "option" value=1>
                    <label for="option"><span><span></span></span>store</label>
                </div>
            </div>
        </form>
        <!--BagianProduk rencananya pake PHP di echo satu-satu-->
        <div id = "search">
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

            try {
                com.marketplace.Marketplace_Service service = new com.marketplace.Marketplace_Service();
                com.marketplace.Marketplace port = service.getMarketplacePort();
                // TODO process result here
                java.util.List<com.marketplace.Product> result = null;

                String url = "http://localhost:8082/IdentityServices/TokenServlet";
                URL iurl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection)iurl.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Send POST output.
                connection.setRequestMethod("POST");
                java.io.DataOutputStream printout = new java.io.DataOutputStream(connection.getOutputStream ());
                String token = (String)session.getAttribute("token");

    //            out.println(token);
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
                String username = "";
                String consignee = "";
                String address = "";
                String postalcode = "";
                String phonenumber = "";
                int product_id = 0;
                if(connection.getResponseMessage().contains("invalid")) {
                    error = connection.getResponseMessage();
                    username = "error";
                    response.sendRedirect("login.jsp");
                }
                else {
                    username = connection.getHeaderField("username");
                    consignee = connection.getHeaderField("fullname");
                    address = connection.getHeaderField("address");
                    postalcode = connection.getHeaderField("postalcode");
                    phonenumber = connection.getHeaderField("phonenumber");
                    user_id = Integer.parseInt(connection.getHeaderField("user_id"));
                }

                if(request.getParameter("search")!=null){
                    result = port.searchProduct(request.getParameter("search"), Integer.parseInt(request.getParameter("option")));
                }else{
                    result = port.getAllProduct();
                }
                if(result != null && result.size()>0){
                    SimpleDateFormat ft = new SimpleDateFormat("E, dd M yyyy");
                    SimpleDateFormat tt = new SimpleDateFormat("HH.mm");
                    for(int i =0; i<result.size();i++){
                        String url2 = "http://localhost:8082/IdentityServices/IdServlet";
                        URL iurl2 = new URL(url2);
                        HttpURLConnection connection2 = (HttpURLConnection)iurl2.openConnection();
                        connection2.setDoOutput(true);
                        connection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                        // Send POST output.
                        connection2.setRequestMethod("POST");
                        java.io.DataOutputStream printout2 = new java.io.DataOutputStream(connection2.getOutputStream ());
                //        out.println(token);
                        Product temp = result.get(i);
                        String content2 = "user_id=" + temp.getUserId();
                        printout2.writeBytes (content2);
                        printout2.flush (); 
                        printout2.close ();  

                        java.io.BufferedReader reader2 = new java.io.BufferedReader(new java.io.InputStreamReader(
                        (java.io.InputStream) connection2.getContent()));

                        String usernameproduct = connection2.getHeaderField("username");



                        Date datetemp = temp.getCreatedAt().toGregorianCalendar().getTime();
                        int totallike = port.getLike(temp.getPId());
                        int status = port.getLikeStatus(temp.getPId(), user_id);
                        int totalpurchase = port.getTotalPurchase(temp.getPId());
                        out.print("<div class = 'product'>");
                                out.print("<div>"
                                        +   "<div class='product-date'>"+usernameproduct+"</div>");
                                        out.print("<div class='product-time'>added this on "+ft.format(datetemp)+" at "+tt.format(datetemp)+"</div>"
                                        + "</div>");
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
                                        + "<a class= '");
                                if(status != 1){
                                        out.print("color-blue");
                                }else{
                                        out.print("red");
                                }  
                                out.print(" like font-bold'  href ='/StackExchangeClient/TestServ?productid="+temp.getPId()+"&userid="+user_id+"'>");//onclick = 'like(this.id,"+")'>");
                                if(status != 1){
                                        out.print("LIKE");
                                }else{
                                        out.print("LIKED");
                                }  
                                out.print("</button>"
                                        + "<a class='link color-green font-bold' href ='confirmation_purchase.jsp?id_product="+temp.getPId()+"'>"
                                        + "<span >BUY</span></a>"
                                    + "</div>"
                                + "</div>"
                                + "<hr class='full' />"
                            + "</div>");
                    }
                }else{
                    out.print("Product Not Found");
                }
            } catch (Exception ex) {
                out.println("Result = "+ex);
            }
        }
    }
%>

        </div>
        <script type="text/javascript" src="js/catalog.js"></script>
    </body>
</html>
