<%-- 
    Document   : login
    Created on : Nov 7, 2016, 12:32:00 PM
    Author     : user-BL
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  
    String user = request.getParameter("username");
    String pass = request.getParameter("password");
    String error = "";
    
    if(user != null && pass != null && !user.equals("") && !pass.equals("")){
        String url = "http://localhost:8082/IdentityServices/LoginServlet";
        URL iurl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)iurl.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // Send POST output.
        connection.setRequestMethod("POST");
        java.io.DataOutputStream printout = new java.io.DataOutputStream(connection.getOutputStream ());
        String content = "username=" + user + "&password=" + pass;
        printout.writeBytes (content);
        printout.flush (); 
        printout.close ();
        
        //retrieve response from IS
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(
            (java.io.InputStream) connection.getContent()));

        String line;
        if(connection.getResponseMessage().contains("Failed")) {
            error = connection.getResponseMessage();
        }
        else {
            String token = connection.getHeaderField("token");
            String uname = connection.getHeaderField("username");
            String userid = connection.getHeaderField("userid");
            session.setAttribute("token", token);
            session.setAttribute("username", uname);
            session.setAttribute("userid", userid);
            Timestamp expire = new Timestamp(System.currentTimeMillis());
            int duration = Integer.parseInt(connection.getHeaderField("expiryTime"));
            long x = ((duration * 60)) * 1000;
            expire.setTime(expire.getTime() + x);
            session.setAttribute("expire", expire);
            response.sendRedirect("catalog.jsp");
        }
    } else if(user != null && pass != null && (user.equals("") || pass.equals(""))){
        // handle empty form or incomplete form 
        error = "Please enter username and password !";
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
	<link rel="stylesheet" type ="text/css" href="css/style.css">
    </head>
    <body class="body-center helvetica">
	<div class = "text-align-center arial">
            <h1><span class="color-red">Sale</span><span class="color-blue">Project</span></h1>
	</div>
	
	<div class = "border-bottom ">
            <h2>Please login</h2>
	</div>
	
	<div>
            <form method="POST" action="login.jsp">
                <span class="font-small">Email or Username</span><br><input type="text" name="username" class="input-text">
                <span class="font-small">Password</span><br><input type="password" name="password"  class="input-text"><br><br>
                <strong style="color:red;"><%out.println(error);%></strong><br>
                <input type="submit" value="LOGIN" name="login" class="float-right button">
            </form>
	</div>	
	<br><br><br>
	<p class="font-small"><strong>Don't have an account yet? Register <a href = "register.jsp" class="link"> here </a></strong></p>
    </body>
</html>