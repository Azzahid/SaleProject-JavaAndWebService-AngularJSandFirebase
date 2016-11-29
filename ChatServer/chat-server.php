<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST');

require_once('db.php');

function broadcastTopic() {
  $url = 'https://fcm.googleapis.com/fcm/send';
  $data = array("data" => array(
      "message"=> "Someone login"
    ),
    "to" => '/topics/movies'
  );
  $options = array(
    'http' => array(
      'method'  => 'POST',
      'content' => json_encode( $data ),
      'header'=>  "Content-Type: application/json\r\n" .
                  "Authorization:key=AAAAQx0rFL4:APA91bHitlPibrVEsCmaPoG29F-09VbBjjuS6y7YQYA0hrFrk3lx4BCVC_nEL6eZbxZ5HVNE-49G8wJbooAgZGi3WLnqYN6QTFxdY8cojghR2-Mq04JzXfO1i0_xSotTpgFAKtDN9JZoqirw9lsYOVKZzGy4jNujDQ\r\n"
      )
  );
  $context  = stream_context_create( $options );
  $result = file_get_contents( $url, false, $context );
  $response = json_decode( $result );
  echo $result;
}

function listenTopic($token) {
  $url = ('https://iid.googleapis.com/iid/v1/'.$token.'/rel/topics/movies');
  // echo $url;
  $data = array();
  $options = array(
    'http' => array(
      'method'  => 'POST',
      'content' => json_encode($data),
      'header'=>  "Content-Type: application/json\r\n" .
                  "Authorization:key=AAAAQx0rFL4:APA91bHitlPibrVEsCmaPoG29F-09VbBjjuS6y7YQYA0hrFrk3lx4BCVC_nEL6eZbxZ5HVNE-49G8wJbooAgZGi3WLnqYN6QTFxdY8cojghR2-Mq04JzXfO1i0_xSotTpgFAKtDN9JZoqirw9lsYOVKZzGy4jNujDQ\r\n"
      )
  );
  $context  = stream_context_create( $options );
  $result = file_get_contents( $url, false, $context );
  $response = json_decode( $result );
  echo $result;
}

function checkIfUserExists($username) {
  $conn = connect_db();

  $sql = "SELECT * FROM chat_user WHERE username = '$username'";
  $result = mysqli_query($conn,$sql);
  if (mysqli_num_rows($result) != 0) {
     return true;
  }
  else {
    return false;
  }
  $conn->close();
}

if($_GET['code'] == 'registerClient') {
    $token = $_GET['token'];
    $username = $_GET['username'];
    $email = $_GET['email'];

    broadcastTopic();
    listenTopic($token);

    if(checkIfUserExists($username)) {
      // echo 'user exists';
      $conn = connect_db();

      $sql = "UPDATE chat_user SET status='1', token='$token' WHERE username = '$username'";
      $result = mysqli_query($conn,$sql);
      if ($result) {
         echo $_GET['callback'] . '(' . "{'response' : 'OKE1'}" . ')';
      }
      else {
        echo $_GET['callback'] . '(' . "{'response' : 'error mysql'}" . ')';
      }
      $conn->close();
    }
    else {
      // echo 'user not exists';
      $conn = connect_db();

      $sql = "INSERT INTO chat_user (username, email, token, status) VALUES ('$username', '$email', '$token', '1')";
      $result = mysqli_query($conn,$sql);
      if ($result) {
         echo $_GET['callback'] . '(' . "{'response' : 'OKE2'}" . ')';
      }
      else {
        echo $_GET['callback'] . '(' . "{'response' : 'error mysql'}" . ')';
      }
      $conn->close();
    }
}
if($_GET['code'] == 'checkingOnlineUsers') {
  $username = $_GET['username'];
  $conn = connect_db();
  $sql = "SELECT * FROM chat_user WHERE status = '1' AND username != '$username'";
  $result = mysqli_query($conn,$sql);
  if ($result) {
      $data = array();
      if ($result->num_rows > 0) {
          // output data of each row
          while($row = $result->fetch_assoc()) {
              $x = array("username" => $row['username'], "token" => $row['token']);
              array_push($data, $x);
          }
      } else {
          // echo "0 results";
      }
      $response = array("data" => $data,
        "response" => 'OKE'
      );
     echo json_encode( $response['data'] );

  }
  else {
    echo $_GET['callback'] . '(' . "{'response' : 'error mysql'}" . ')';
  }
}
if($_GET['code'] == 'sendChat') {
  $to = $_GET['to'];
  $message = $_GET['message'];
  $senderUsername = $_GET['senderUsername'];
  $url = 'https://fcm.googleapis.com/fcm/send';
  $data = array("data" => array(
      "message"=> $message,
      "username" => $senderUsername,
      "time" => date('Y-m-d H:i'),
    ),
    "to" => $to
  );
  $options = array(
    'http' => array(
      'method'  => 'POST',
      'content' => json_encode( $data ),
      'header'=>  "Content-Type: application/json\r\n" .
                  "Authorization:key=AAAAQx0rFL4:APA91bHitlPibrVEsCmaPoG29F-09VbBjjuS6y7YQYA0hrFrk3lx4BCVC_nEL6eZbxZ5HVNE-49G8wJbooAgZGi3WLnqYN6QTFxdY8cojghR2-Mq04JzXfO1i0_xSotTpgFAKtDN9JZoqirw9lsYOVKZzGy4jNujDQ\r\n"
      )
  );

  $context  = stream_context_create( $options );
  $result = file_get_contents( $url, false, $context );
  $response = json_decode( $result );
}
if($_GET['code'] == 'deleteOnlineUser') {
    $token = $_GET['token'];
    $conn = connect_db();

    $sql = "UPDATE chat_user SET status='0' WHERE token='$token'";
    $result = mysqli_query($conn,$sql);
    if ($result) {
       echo $_GET['callback'] . '(' . "{'response' : 'OKE1'}" . ')';
    }
    else {
      echo $_GET['callback'] . '(' . "{'response' : 'error mysql'}" . ')';
    }
    $conn->close();
}
