<!doctype html>
<html lang="ja">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/style.css">

<title>よくわかるPHPの教科書</title>
</head>
<body>
<header>
<h1 class="font-weight-normal">よくわかるPHPの教科書</h1>    
</header>

<main>
<h2>ラジオボタンの値を取得する</h2>
<pre>


    <?php
    $ran=rand(0,2);
    $hand=htmlspecialchars($_GET['hand'], ENT_QUOTES);

   $buf = [1, 2, 0];
   if($buf[$hand] == $ran){
        echo "勝った!";
    }else if($hand == $ran){
        echo "あいこ";
    }else{
        echo "負けた";
    }

    ?>
    <p>プレイヤー:<?php
    if($hand==0){
        print("<img src='gu.jpg' width='200px' height='200px'>");
    };
    if($hand==1){
        print("<img src='jian.jpg' width='200px' height='200px'>");
    };
    if($hand==2){
        print("<img src='pa.jpg' width='200px' height='200px'>");
    };
        ?></p>

    <p>NPC:<?php if($ran==0){
        print("<img src='gu.jpg' width='200px' height='200px'>");
    };
    if($ran==1){
        print("<img src='jian.jpg' width='200px' height='200px'>");
    };
    if($ran==2){
        print("<img src='pa.jpg' width='200px' height='200px'>");
    };
    ?>
        </p>
</pre>
</main>
</body>
</html>