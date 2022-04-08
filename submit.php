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
性別： <?php print(htmlspecialchars($_GET['gender'], ENT_QUOTES)); ?>

    <?php
    $ran=rand(0,2);
    $hand=htmlspecialchars($_GET['hand'], ENT_QUOTES);

   $buf = [0=>1,
            1=>2,
             2=>0];
   if($buf[$hand] == $ran){
        echo "勝った!";
    }else if($hand == $ran){
        echo "引き分け";
    }else{
        echo "負けた";
    }

    ?>
    <p>プレイヤー:<?php
    if($hand==0){
        print("グー");
    };
    if($hand==1){
        print("チョキ");
    };
    if($hand==2){
        print("パー");
    };
        ?></p>

    <p>NPC:<?php if($ran==0){
        print("グー");
    };
    if($ran==1){
        print("チョキ");
    };
    if($ran==2){
        print("パー");
    };
    ?>
        </p>
</pre>
</main>
</body>
</html>