<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Player" %>
<%
Player player = (Player) session.getAttribute("player");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
	<p>賭け金を入力してください<br>
	<span class="betIn-name">${player.name}</span>さんの所持チップ数: <span class="betIn-chip">${player.chip}</span></p>
	<p style="color:red;">※1～${player.chip}の範囲で入力してください。</p>

	<form action="/RegisterBet" method="post">
		賭け金:<input type="number" name="bet" id="betIn" required>
		<input class="betIn-submit" type="submit" value="決定">
	</form>

	<script type="text/javascript">
		document.getElementById('betIn').focus();
	</script>

	<div class="betInResult"><a href="/Result">ゲームを終了する</a></div>
</body>
</html>