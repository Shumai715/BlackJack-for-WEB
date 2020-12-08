<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String reason = (String)request.getAttribute("reason"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
	ログインに失敗しました。
	<% if(reason.equals("name")){ %>
		<p style="color:red;">※入力された名前は登録されていません。</p>
	<%}else if(reason.equals("pass")){ %>
		<p style="color:red;">※入力されたパスワードが間違っています。</p>
	<%} %>

	<a href="/">トップページへ</a>

</body>
</html>