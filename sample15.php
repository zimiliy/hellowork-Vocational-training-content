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
<h2>XMLを読み込む</h2>
<pre>
<?php
$xmlTree = simplexml_load_file('https://news.yahoo.co.jp/rss/topics/it.xml');
foreach($xmlTree->channel->item as $item) :
?>
・<a href="<?php print($item->link); ?>"><?php print($item->title); ?></a>
<?php
endforeach;
?>
</pre>
    <pre>
<?php
$xmlTree = simplexml_load_file('https://news.kddi.com/kddi/corporate/newsrelease/rss/kddi_news_release.xml');
foreach($xmlTree->channel->item as $item) :
    ?>
    ・<a href="<?php print($item->link); ?>"><?php print($item->title); ?></a>
<?php
endforeach;
?>
</pre>
    <pre>
        <?php
        $file=file_get_contents('https://h2o-space.com/feed/json');
        $json=json_decode($file);
        foreach ($json->items as $item):
        ?>
    ・<a href="<?php print($item->url); ?>"><?php print($item->title); ?></a>
        <?php
        endforeach;
        ?>
    </pre>
    <pre>
        <form action="submit.php" method="get">
            <label for="my_name">お名前：</label>
            <input type="text" id="my_name" name="my_name" maxlength="255" value="">
            <input type="submit" value="送信する">
        </form>
    </pre>
    <pre>


    </pre>
</main>
</body>
</html>
