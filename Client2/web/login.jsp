<%-- 
    Document   : login2
    Created on : Nov 29, 2016, 5:49:15 AM
    Author     : YUKI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="login.jsp" id="loginForm">
            Email or Username<br><input type="text" name="username" /><br>
            Password<br><input type="password" name="password"  /><br>
            <input type="submit" value="LOGIN" name="login" />
        </form>
    </body>
</html>
<% 
    if("POST".equalsIgnoreCase(request.getMethod())) {
        String username = request.getParameter("username");
        
        session.setAttribute("username", username);
//        session.setAttribute("firebaseToken", firebaseToken);
        response.sendRedirect("catalog.jsp");
    }
%>
        