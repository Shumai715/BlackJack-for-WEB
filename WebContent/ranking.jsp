<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List, java.util.ArrayList, model.PlayRecord" %>

<%
List<PlayRecord> rankingList = (List<PlayRecord>) session.getAttribute("rankingList");
PlayRecord nowRecord = (PlayRecord)session.getAttribute("nowRecord");
String userName = (String)session.getAttribute("userName");


int rank = 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<h1>ランキング　☆TOP10☆</h1>
<%if(rankingList != null){ %>
	<table class="ranking" border="1">
		<tr>
		  <th class="playHistory-cell">順位</th>
		  <th class="playHistory-cell">名前</th>
		  <th class="playHistory-cell">スコア</th>
		  <th class="playHistory-cell">日付</th>
		</tr>
		<%for(int i = 0; i < rankingList.size() && i < 10; i++){%>
		   <%if(rankingList.get(i).getPlayerName().equals(userName)){%>
		       <%if(nowRecord != null && rank == 0 ){ %>
     		        <%if(rankingList.get(i).getScore() == nowRecord.getScore() &&
     		             rankingList.get(i).getDate().equals(nowRecord.getDate()) ){ %>
		                      <%rank = i + 1; %>
		     	              <tr class="nowRecord">
		     	    <%} else { %>
		     	              <tr class="userRecord">
		     	    <%} %>
		       <%}else{ %>
			 <tr class="userRecord">
			 <%} %>
		   <%}else{ %>
		   	 <tr>
		   <%} %>
		  	  <td class="playHistory-cell"><%= i+1 %></td>
  			  <td class="playHistory-cell"><%= rankingList.get(i).getPlayerName() %></td>
			  <td class="playHistory-cell"><%= rankingList.get(i).getScore() %></td>
			  <td class="playHistory-cell"><%= rankingList.get(i).getDate() %></td>
			</tr>
		<%} %>
	</table>
	<%if(nowRecord != null){ %>
		<p>今回のスコア：<%= nowRecord.getScore() %><br>
		<%if(rank != 0){ %>
			順位: <%= rank %>位
		<%}else{ %></p>
			順位: 圏外
		<%} %>
	<%} %>

<%} else{%>
		<p>プレイした人がいないため、ランキングがありません。</p>
<%} %>
<div class="ranking-adiv">
<div class="ranking-eachDiv"><a class="ranking-a" href="/MyPage">マイページへ</a></div>
<div class="ranking-eachDiv"><a class="ranking-a" href="/allRanking.jsp">スコア全体ランキングへ</a><br></div>
<div class="ranking-eachDiv"><a class="ranking-a" href="/userRanking.jsp">ユーザー別最高スコアランキングへ</a></div>
</div>
</body>
</html>