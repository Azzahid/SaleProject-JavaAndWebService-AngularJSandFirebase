<%-- 
    Document   : logout.jsp
    Created on : Nov 13, 2016, 2:50:53 PM
    Author     : user-BL
--%>

<%@page import="java.net.HttpURLConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.net.URL"%>
<!DOCTYPE html>
<html>
    <head>
        
    </head>
    <body>
        <!--Jquery-->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="https://www.gstatic.com/firebasejs/3.6.1/firebase.js"></script>
    <script type="text/javascript" src="chat.js"></script>
    <script>
        deleteToken();
    </script>
    <a href="login.jsp">Back to login...</a>
    </body>
</html>

<%  
    String user = (String)session.getAttribute("username");
    String token = (String)session.getAttribute("token");
    String error = "";

    String url = "http://localhost:8082/IdentityServices/logoutServlet";
    URL iurl = new URL(url);
    HttpURLConnection connection = (HttpURLConnection)iurl.openConnection();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

    // Send POST output.
    connection.setRequestMethod("POST");
    java.io.DataOutputStream printout = new java.io.DataOutputStream(connection.getOutputStream ());
    String content = "username=" + user + "&token=" + token;
    printout.writeBytes (content);
    printout.flush (); 
    printout.close ();

    //retrieve response from IS
    java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(
        (java.io.InputStream) connection.getContent()));

    String line;
    session.setAttribute("userid", null);
    session.setAttribute("username", null);
    session.setAttribute("token", null);
    session.setAttribute("expire", null);
    session.invalidate();
    //Thread.sleep(5000); // sleep 5 seconds
    //response.sendRedirect("login.jsp");
%>
