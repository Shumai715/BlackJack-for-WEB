<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Dealer,model.Player,model.Card,java.util.List,java.util.ArrayList, model.Deck" %>

<%
Dealer dealer = (Dealer) session.getAttribute("dealer");
List<Card> dealerHand = dealer.getHand();

Player player= (Player)session.getAttribute("player");
List<Card> playerHand = player.getHand();


Deck deck = (Deck)session.getAttribute("deck");

String result = (String)session.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
	<h1><%= result %></h1>
	<div class="play-bord">
		<div class="bord-dealer">
			<p>ディーラー: <span class="point">${dealer.handSum}</span>

			<div class="hand dealerHand">

				<% for(int i = 0; i < dealerHand.size(); i++){ %>
					<span class="handCard">
						<% if(dealerHand.get(i).markInt == 1 || dealerHand.get(i).markInt == 3){%>
							<span style="color:red;"><span class="cardContent">
								<%= dealerHand.get(i).markStr %><%= dealerHand.get(i).numberStr %></span>
							</span>
						<%}else{ %>
							<span class="cardContent"><%= dealerHand.get(i).markStr %><%= dealerHand.get(i).numberStr %></span>
						<%} %>
					</span>
				<%} %>
			</div>
		</div>

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
								<span class="cardContent"><%= playerHand.get(i).markStr %><%= playerHand.get(i).numberStr %></span>
							</span>
						<%}else{ %>
							<span class="cardContent"><%= playerHand.get(i).markStr %><%= playerHand.get(i).numberStr %></span>
						<%} %>
					</span>
				<%} %>
			</div>

			<p>${player.name}さん：
			<%if(player.getHandSum() > 21){ %>
				<span class="point">${player.handSum}　　<span class="bust">バースト!</span></span></p>
			<%}else{ %>
				<span class="point">${player.handSum}</span></p>
			<%} %>
		</div>
	</div>

	<p class="chip">所持チップ：${player.chip}枚</p>
	<br>
	<%if(deck.getDeckEndFlag() || player.getChip() < 1){ %>
		<%if(deck.getDeckEndFlag()){ %>
			<p>山札が無くなったので、ゲーム終了です</p>
		<%}else{%>
			<p>所持チップが無くなったので、ゲーム終了です</p>
		<%} %>
		<a href="/Result">リザルト画面へ</a>
	<%}else{%>
		<a href="/GameReset">次のゲームへ</a> 　　　<a href="/Result">ゲームを終了する</a>
	<%} %>

</body>
</html>
