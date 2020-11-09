<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Dealer,model.Player,model.Card,java.util.List,java.util.ArrayList,model.Deck" %>

<%
Dealer dealer = (Dealer) session.getAttribute("dealer");
List<Card> dealerHand = dealer.getHand();

Player player= (Player)session.getAttribute("player");
List<Card> playerHand = player.getHand();

Deck deck = (Deck)session.getAttribute("deck");


%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body class="main-body">
<div  class="main-container">
<div class="play-bord">
<div class="bord-dealer">
<p>ディーラー: <span class="point">？</span></p>
<div class="hand dealerHand">
	<span class="handCard">
		<% if(dealerHand.get(0).markInt == 1 || dealerHand.get(0).markInt == 3){%>
			<span style="color:red;">
		<%} %>

		<span class="cardContent"><%= dealerHand.get(0).markStr %><%= dealerHand.get(0).numberStr %></span>

		<% if(dealerHand.get(0).markInt == 1 || dealerHand.get(0).markInt == 3){%>
			</span>
		<%}%>
	</span>
	<span class="handCard">
		<span style="font-size:2rem;margin:0 8px;">？</span>
	</span>

</div>
</div>
<%if(deck.getDeckEndFlag()) {%>
<p class="DeckEndMessege">※山札が無くなったので、これが最後のゲームになります。</p>
<%} %>
<div class="bord-player">
<%if(player.getBet() < 1000){ %>
	<div class="bet-999 bord-bet">
<%}else if(player.getBet() < 100000){ %>
	<div class="bet-99999 bord-bet">
<%}else{ %>
	<div class="bet-mugen bord-bet">
<%} %>
	<span>${player.bet}枚</span>
</div>
<div class="hand playerHand">
	<% for(int i = 0; i < playerHand.size(); i++){ %>
		<span class="handCard">
		<% if(playerHand.get(i).markInt == 1 || playerHand.get(i).markInt == 3){%>
			<span style="color:red;">
		<%} %>

		<span class="cardContent"><%= playerHand.get(i).markStr %><%= playerHand.get(i).numberStr %></span>

		<% if(playerHand.get(i).markInt == 1 || playerHand.get(i).markInt == 3){%>
			</span>
		<%}%>
		</span>
	<%} %>
</div>
<p>${player.name}さん：
<%if(player.getExist1()){ %>
	<span class="point"><%= player.getHandSum() - 10 %></span>or
<%} %>
<span class="point">${player.handSum}</span></p>
</div>
</div>

<p class="chip">所持チップ：${player.chip}枚</p>

<form action="/GameMain" method="post">
<p class="action">アクション：<br>
<%if(player.getHand().size() == 2 && player.getHandSum() == 21){ %>
	<label><input type="radio" name="action" value="hit">Hit … 一枚引く<br></label>
	<label><input type="radio" name="action" value="stand"  checked="checked">Stand … この手札で勝負<br></label>
<%}else{ %>
	<label><input type="radio" name="action" value="hit" checked="checked">Hit … 一枚引く<br></label>
	<label><input type="radio" name="action" value="stand">Stand … この手札で勝負<br></label>
<%} %>

<%if(player.getChip() >= player.getBet() && player.getHand().size() == 2){ %>
	<label><input type="radio" name="action" value="double">Double Down(初手のみ) … 賭け金を二倍し、一枚引いて勝負<br></label>
<%}else{%>
	<label><input type="radio" name="action" value="double" disabled>Double Down(初手のみ) … 賭け金を二倍し、一枚引いて勝負<br></label>
<%} %>

<%if(player.getHand().size() == 2){ %>
	<label><input type="radio" name="action" value="fold">Fold (初手のみ) … 勝負を降りる（ベッドの半分が戻ってきます）</p></label>
<%}else{%>
	<label><input type="radio" name="action" value="fold" disabled>Fold (初手のみ) … 勝負を降りる（ベッドの半分が戻ってきます）</p></label>
<%} %>
<p><input type="submit" value="決定"><br>
※一回だけクリックしてください。</p>

<p style="color:red;font-size:12pt;">※「Double Down」と「Fold」は初手のみ選択可<br>
また、ベッド額が所持チップより多い場合も「Double Down」は選択できません。</p>
</form>
</div>
<div class="rule main-rule">
		<h2>・ルール</h2>
		<p>手札の数字の合計がディーラーより21に近ければあなたの勝ちです。ただし、21を超えてしまうと負けになるので注意してください。<br>
		ディーラーは手札の合計が17以上になるまで引きます。どちらもバーストした場合、プレイヤーの負けとなります。</p>
		<h3>・数字の数え方</h3>
		<p>　1　… 1 or 11　/　2～10 … 数字通り　/　J～K  … 10</p>

		<h3>・配当</h3>
		<p>通常時→1倍　/　ブラックジャック時→1.5倍（ブラックジャック ＝ 初手で21）</p><br>
		<p>注意：このゲームは1デックで行います。山札のカードを全て引き終わった回が最後の勝負です。なお、山札のカードが無くなった後は、捨て札からランダムで引かれます。</p>
</div>

</body>
</html>
