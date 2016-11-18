<%@page import="java.net.URL"%>
<%@page import="java.net.HttpURLConnection"%>
<%@ page session="true" %>
<%
        
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

    String username = "";
    if(connection.getResponseMessage().contains("invalid")) {
        username = "error";
        response.sendRedirect("login.jsp");
    }
    else {
        username = connection.getHeaderField("username");
    }
   
%>
<div class = "text-align-center arial">
    <h1><span class="color-red">Sale</span><span class="color-blue">Project</span></h1>
</div>
<p class="text-align-right">Hi, <%out.println(username);%><br />
<a href="logout.jsp" class="link color-red">logout</a></p>
<div>
    <ul>
        <li><a href="catalog.jsp"><span>Catalog</span></a></li>
        <li><a href="your_products.jsp"><span>Your Products</span></a></li>
        <li><a href="addProduct.jsp"><span>Add Product</span></a></li>
        <li><a href="sales.jsp"><span>Sales</span></a></li>
        <li><a href="purchases.jsp"><span>Purchases</span></a></li>
    </ul>
</div>