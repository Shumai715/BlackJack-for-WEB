<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
	<h1>✨BLACK JACKへようこそ！✨</h1><br>
	<div class="topPage-div">
		<h2>ログイン</h2>
		<form action="/Login" method="post">
			<div class="loginForm" >
				ニックネーム<br>
				<input type="text" name="name" pattern="\S+" title="スペースが入っている名前は無効です" required>
			</div>
			<div class="loginForm">
				パスワード<br>
				<input type="password" name="pass" pattern="\S+" title="スペースが入っている名前は無効です" required>
			</div>
			<input class="loginButton" type="submit" value="ログイン"><br>
		</form>
	</div>

	<div class="topPage-div">
		<h2>初めての方はこちら</h2>
		<form action="/registerUserForm.jsp">
			<p>まだ登録をしていない方は「新規登録」をクリック！
			<input class="registerButton" type="submit" value="新規登録">
			</p>
		</form>
	</div>

	<p>ソースコードはこちら↓<br>
	<a href="https://github.com/Shumai715/BlackJack-for-WEB">https://github.com/Shumai715/BlackJack-for-WEB</a></p>

</body>
</html>