<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<main>
    <pre>
        <?php
        $doc = file_get_contents('./news.txt');
        $doc.="<br/>2018-06-02 ニュースを追加しました。";
        file_put_contents('./news.txt',$doc);
        readfile('./news.txt');
        ?>
    </pre>
</main>
</body>
</html>