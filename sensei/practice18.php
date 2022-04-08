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
<?php
$player = htmlspecialchars($_REQUEST['janken'],ENT_QUOTES);
$npc = rand(0,2);
$judge="あなたの負け";
if($player == $npc){
	$judge="引き分け";
}else if(($player==0 &&$npc == 1)||($player==1 &&$npc == 2)||($player==2 &&$npc == 0)){
	$judge="あなたの勝ち";
}
$hand = ['グー','チョキ','パー'];
?>
<p>プレイヤー：<?= $hand[$player] ?></p>
<p>NPC：<?= $hand[$npc] ?></p>
<p><?= $judge ?></p>

<?php //張さんのアルゴリズム
$judgement = [1,2,0];
$judge="あなたの負け";
if($player == $npc){
	$judge="引き分け";
}else if($judgement[$player] == $npc){
	$judge="あなたの勝ち";
}
$hand = ['グー','チョキ','パー'];
?>

<p>プレイヤー：<?= $hand[$player] ?></p>
<p>NPC：<?= $hand[$npc] ?></p>
<p><?= $judge ?></p>
    //猜拳游戏
    <form action="practice18.php" method="get">
        <select name="janken">
            <option value="0">グー</option>
            <option value="1">チョキ</option>
            <option value="2">パー</option>
        </select>
        <input type="submit" value="送信">
    </form>


</body>    
</html>