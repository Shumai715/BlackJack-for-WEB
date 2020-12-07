<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.User, model.Player, java.util.List, java.util.ArrayList" %>

<%
Player player = (Player)session.getAttribute("player");
String messeage = player.getName() + "さんのスコアは" + player.getChip() + "でした！！";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body class="playResult-body">
	<p>お疲れさまでした！</p>
	<p>今回のスコアは、<span class="playResult-span">${player.chip}</span>でした！！</p>
	<% if(player.getChip() <= 0) {%>
		<p>残念ながら0点だったため、ランキングには反映されません。<br>
  	    個人のプレイ履歴には保存されます。</p>
	<%} %>

	<div class="tweet">
		↓結果をツイートする。<br>
		<a href="https://twitter.com/intent/tweet?url=https://blackjack715.herokuapp.com/" class="twitter-hashtag-button" data-text=<%= messeage %> data-show-count="false">Tweet</a>
		<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
	</div>

	<div class="playResult-container">
		<a class="playResult-a" href="/PlayReset?page=top">マイページへ</a>
		<a class="playResult-a" href="/PlayReset?page=rank">ランキングへ</a>
	</div>
</body>
</html>