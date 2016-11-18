<%-- 
    Document   : register
    Created on : Nov 7, 2016, 20:32:00 PM
    Author     : shirayuki97
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  
    String full_name = request.getParameter("full_name");
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String pass = request.getParameter("pass");
    String confirm_pass = request.getParameter("confirm_pass");
    String full_address = request.getParameter("full_address");
    String postal_code = request.getParameter("postal_code");
    String phone_number = request.getParameter("phone_number");
    
    String error = "";
    
    if("POST".equalsIgnoreCase(request.getMethod())) {
        
        String url = "http://localhost:8082/IdentityServices/RegisterServlet";
        URL iurl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)iurl.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // Send POST output.
        connection.setRequestMethod("POST");
        java.io.DataOutputStream printout = new java.io.DataOutputStream(connection.getOutputStream ());
        String content = "full_name=" + full_name 
                + "&username=" + username
                + "&email=" + email
                + "&pass=" + pass
                + "&full_address=" + full_address
                + "&postal_code=" + postal_code
                + "&phone_number=" + phone_number;
        printout.writeBytes (content);
        printout.flush (); 
        printout.close ();

        // retrieve response from IS
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(
            (java.io.InputStream) connection.getContent()));
        String line;
        
        if(connection.getResponseMessage().contains("failed")) {
            error = connection.getResponseMessage();
        }
        else {
            String token = connection.getHeaderField("token");
            session.setAttribute("token", token);
            Timestamp expire = new Timestamp(System.currentTimeMillis());
            int duration = Integer.parseInt(connection.getHeaderField("expiryTime"));
            long x = ((duration * 60)) * 1000;
            expire.setTime(expire.getTime() + x);
            session.setAttribute("expire", expire);
            response.sendRedirect("catalog.jsp");
        }
        
        
        
        

//        while ((line = reader.readLine()) != null) {
//            out.println(line + "<br>");
//        }

        
    }
    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type ="text/css" href="css/style.css">
    </head>
    <body class="body-center helvetica">
        <div class = "text-align-center arial">
            <h1><span class="color-red">Sale</span><span class="color-blue">Project</span></h1>
        </div>
        <div class = "border-bottom ">
            <h2>Please register</h2>
        </div>
        <form method="POST" action="register.jsp" id="register" name="register">
            <div>
                <label for="full_name">Full Name</label><br />
                <input type="text" name="full_name" class="input-text">
            </div>
            <div>
                <label for="username">Username</label><br />
                <input type="text" name="username" class="input-text">
            </div>
            <div>
                <label for="email">Email</label><br />
                <input type="text" name="email" class="input-text">
            </div>
            <div>
                <label for="pass">Password</label><br />
                <input type="password" name="pass" class="input-text">
            </div>
            <div>
                <label for="confirm_pass">Confirm Password</label><br />
                <input type="password" name="confirm_pass" class="input-text">
            </div>
            <div>
                <label for="full_address">Full Address</label><br />
                <textarea name="full_address" rows="5" cols="50" class="input-textarea"></textarea>
            </div>
            <div>
                <label for="postal_code">Postal Code</label><br />
                <input type="text" name="postal_code" class="input-text">
            </div>
            <div>
                <label for="phone_number">Phone Number</label><br />
                <input type="text" name="phone_number" class="input-text">
            </div>
            <strong style="color:red;"><%out.println(error);%></strong><br>
            <div>
                <input type="button" value="REGISTER" name="registerr" onclick="validateform();" class="button float-right">
            </div>
        </form>
        <br>
        <div>
            <p class="font-small"><strong>Already registered? Login <a href="login.jsp" class="link">here</a></strong></p>
        </div>
        <script src="js/register.js"></script>
    </body>
</html>