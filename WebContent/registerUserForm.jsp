<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String alreadyName = (String)request.getAttribute("alreadyName");
if(alreadyName == null){
	alreadyName = "false";
}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body class="registerUserForm-body">
	<h1>新規登録フォーム</h1>
	<%if(alreadyName.equals("true")){ %>
		<p class="alreadyName">※ すでに登録されている名前です。別の名前を入力してください</p>
	<%} %>

	<form action="/RegisterUser" method="post">
		<div class="createForm createName">
			<span>ニックネーム</span>  ：
			<input type="text" name="name" pattern="\S+" title="スペースが入っている名前は無効です"  required>
		</div>

		<div class="createForm">
			パスワード：
			<input type="password" name="pass" pattern="\S+" title="スペースが入っている名前は無効です"  required>
		</div>
		<input class="loginButton" type="submit" value="登録">
	</form>

	<form class="registerForTOP" action="/" method="get">
		<input type="submit" value="戻る">
	</form>
</body>
</html>