<!doctype html>
<html lang="ja">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../sample19/css/style.css">

    <title>よくわかるPHPの教科書</title>


</head>
<body>
<header>
    <h1 class="font-weight-normal">よくわかるPHPの教科書</h1>
</header>

<h2>Practice</h2>

<main>
<pre>
<?php
//取得form的内容
$comment = htmlspecialchars($_REQUEST['my_name'],ENT_QUOTES);
//读取test.txt文件
$doc = file_get_contents('./news_data/test.txt');
//替换文件内容
$doc ='<hr>'.$comment.'<br>投稿者:'.$_SERVER["REMOTE_ADDR"].$doc;//最后的$doc是输出以前输入的内容
//写入文件
file_put_contents('./news_data/test.txt',$doc);
//输出文件内容
readfile('./news_data/test.txt');
?>
</pre>
</main>
</body>
</html>
