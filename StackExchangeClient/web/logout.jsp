<%-- 
    Document   : logout.jsp
    Created on : Nov 13, 2016, 2:50:53 PM
    Author     : user-BL
--%>

<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
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
    response.sendRedirect("login.jsp");
%>