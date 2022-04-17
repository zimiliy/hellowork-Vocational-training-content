<?php
session_start();
require('dbconnect.php');
if(isset($_SESSION['id'])){
    $id=$_SESSION['id'];
}

    
    $users = $db->prepare('SELECT * FROM members WHERE id=?');
    $users->execute(array($id));
    $user = $users->fetch();

    if($_SESSION['id']==$user['id']){
        $del=$db->prepare('DELETE FROM members WHERE id=?');
        $del->execute(array($id));
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=], initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div id="wrap">
    <p>退会しました。</p>
    <a href="login.php">戻る</a>
    </div>
</body>
</html>