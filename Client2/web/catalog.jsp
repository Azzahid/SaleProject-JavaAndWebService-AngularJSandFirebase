<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!--Manifest-->
        <link rel="manifest" href="manifest.json">
        
        <!--AngularJS-->
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    </head>
    <body >
        <%
            String username = "";
            if (session.getAttribute("username") == null) {
                response.sendRedirect("login.jsp");
            }
            else {
                username = (String)session.getAttribute("username");
            }
        %>
        <h1>Client Server</h1>
        <div id="username" value="<%out.print(username);%>"></div>
        <!-- Container for the Table of content -->
        <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-desktop">
          <div class="mdl-card__supporting-text mdl-color-text--grey-600">
            <h3>Messages</h3>
            <div id="messages"></div>
            <h3>Online Users</h3>
            <div id="onlineUsers"></div>
            <div ng-app="clientApp" ng-controller="clientController">
                <ul>
                <li ng-repeat="x in onlineUsers">
                    <input type="checkbox" ng-model="x.show">
                    {{ x.show+ x.username + '('+x.token+')' }}
                    <div ng-show="x.show">
                        <form>
                            <div id="box-{{x.username}}"></div>
                            <input ng-model="x.message" id="message-{{x.username}}"></input>
                            <button ng-click="sendChat(x.token, x.message, x.username)">send</button>
                        </form>
                    </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <a href="logout.jsp" >logout</a></p>
        
        <!--Scripts-->
        <!--Jquery-->
        <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>

        <!-- Firebase -->
        <!-- ********************************************************
             * TODO(DEVELOPER): Update Firebase initialization code:
                1. Go to the Firebase console: https://console.firebase.google.com/
                2. Choose a Firebase project you've created
                3. Click "Add Firebase to your web app"
                4. Replace the following initialization code with the code from the Firebase console:
        -->
        <!-- START INITIALIZATION CODE -->
        <script src="https://www.gstatic.com/firebasejs/3.6.1/firebase.js"></script>
       
        
        <script>
            var app = angular.module("clientApp", []);
            app.controller("clientController", function($scope, $http) {
                var cUsername = $('#username').attr('value');
                console.log('checking online users');                
                $http.get("http://localhost:8083/chat-server.php?code=checkingOnlineUsers&callback=?&username="+cUsername)
                    .then(function (response) {
                        $scope.onlineUsers = response.data;
                    });
                
                $scope.sendChat = function(token, message, username) {
                    $('#box-'+username).append("You: "+message);
                    $('#message-'+username).val("");
                    console.log('sending message...');
                    $.getJSON('http://localhost:8083/chat-server.php?callback=?',{
                        code: "sendChat",
                        to: token,
                        message: message,
                        senderUsername: $('#username').attr('value'),
                      },function(res){
                          alert('Response: '+res.response);
                    });
                };
                
                $scope.updateOnlineUsers = function() {
                    console.log('updating online users');
                    $.getJSON('http://localhost:8083/chat-server.php?callback=?',{
                        code: "checkingOnlineUsers",
                        token: cToken,
                      },function(res){
                          console.log("response: "+res.data);
                          $scope.$apply(function(){
                            $scope.onlineUsers = res.data;
                          });
                    });
                }
            });
        </script>
        <script type="text/javascript" src="chat.js"></script>
    </body>
</html>
