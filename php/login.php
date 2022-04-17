<?php
error_reporting(0);
require('dbconnect.php');

session_start();

if ($_COOKIE['email'] != '') {
	$_POST['email'] = $_COOKIE['email'];
	$_POST['password'] = $_COOKIE['password'];
	$_POST['save'] = 'on';
}

if (!empty($_POST)) {
	// ログインの処理
	if ($_POST['email'] != '' && $_POST['password'] != '') {
    $login = $db->prepare('SELECT * FROM members WHERE email=? AND password=?');
    $login->execute(array(
      $_POST['email'],
      sha1($_POST['password'])
    ));
    $member = $login->fetch();

    if ($member) {
			// ログイン成功
			$_SESSION['id'] = $member['id'];
			$_SESSION['time'] = time();

			// ログイン情報を記録する
			if ($_POST['save'] == 'on') {
				setcookie('email', $_POST['email'], time()+60*60*24*14);
				setcookie('password', $_POST['password'], time()+60*60*24*14);
			}

			header('Location: index.php'); exit();
		} else {
			$error['login'] = 'failed';
		}
	} else {
		$error['login'] = 'blank';
	}
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="../js/jquery-3.6.0.min.js"></script>
<title>ログインする</title>
</head>

<body>
 
<div class="book">
  <div class="cover">
    <span>THE BEETS掲示板</span>
  </div>
  <div class="content">
    <div id="lead">
      <p>メールアドレスとパスワードを記入してログインしてください。</p>
      <p>入会手続きがまだの方はこちらからどうぞ。</p>
      <p>&raquo;<a href="join/">入会手続きをする</a></p>
    </div>
    <form action="" method="post">
      <dl>
        <dt>メールアドレス</dt>
        <dd>
          <input type="text" name="email" size="35" maxlength="255" value="<?php echo htmlspecialchars($_POST['email']); ?>" />
          <?php if ($error['login'] == 'blank'): ?>
          <p class="error">* メールアドレスとパスワードをご記入ください</p>
          <?php endif; ?>
          <?php if ($error['login'] == 'failed'): ?>
          <p class="error">* ログインに失敗しました。正しくご記入ください。</p>
          <?php endif; ?>
        </dd>
        <dt>パスワード</dt>
        <dd>
          <input type="password" name="password" size="35" maxlength="255" value="<?php echo htmlspecialchars($_POST['password']); ?>" />
        </dd>
        <dt>ログイン情報の記録</dt>
        <dd>
          <input id="save" type="checkbox" name="save" value="on">
          <label for="save">次回からは自動的にログインする</label>
        </dd>
      </dl>
      <div>
        <input type="submit" value="ログインする" />
      </div>
    </form>
  </div>
  <div id="foot">
    <p>©Copyright by THE BEETS TEAM。All rights reserved</p>
  </div>
</div>

<Script language="JavaScript">
//定义一个con 绑定.container
        //不需要jQuery支持，也可以方便的获取DOM元素
        const con = document.querySelector('.container');

        //定义两个函数开关(门)
        let isIn = true;//鼠标进去的门,默认打开
        let isOut = false;//鼠标出去的门,默认关闭

        var span;// 给未出生的元素起名span


        //添加监听
        //监听鼠标进去的事件
        con.addEventListener('mouseenter',(e)=>{
            //如果进去的门是打开的,就可以执行这个函数
            if(isIn){
                 //获取进入的鼠标位置
                //生成元素的位置 = 进入点距离窗口的距离-父盒子距离窗口的距离
                //e.clientX;相对于浏览器左上角 
                let inX = e.clientX-e.target.offsetLeft;
                let inY = e.clientY-e.target.offsetTop;

                //创建一个span元素,并且给他对应的出生坐标
                let el = document.createElement('span');
                el.style.left = inX+'px';
                el.style.top = inY+'px';
                con.appendChild(el);

                $('.container span').removeClass('out');//移除出去的动画
                $('.container span').addClass("in");// 添加进入的动画
                span = document.querySelector('.container span');

                isIn = false;//关闭进来的门(不能使用进入的方法)
                isOut = true; //打开出去的门(可以使用出去的方法)

            }
        })

        // 监听鼠标出去的事件
        con.addEventListener('mouseleave',(e)=>{
            if(isOut){
                //获取进入的鼠标位置
                //生成元素的位置 = 进入点距离窗口的距离-父盒子距离窗口的距离
                //e.clientX;相对于浏览器左上角 
                let outX = e.clientX-e.target.offsetLeft;
                let outY = e.clientY-e.target.offsetTop;

                $('.container span').removeClass('in');     // 移除进入的动画
                $('.container span').addClass('out');       // 添加出去的动画
                // 添加出去的坐标
                $('.out').css('left',outX+'px');
                $('.out').css('top',outY+'px');
                isOut = false; // 关闭出去的门
                 // 当动画结束后再删除元素
                 setTimeout(() =>{
                    con.removeChild(span);// 删除元素
                    isIn = true; // 打开进入的门
                 },500)
            }
        })
    </script>
    
</body>
</html>
