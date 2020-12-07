<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList, model.User" %>

<%
List<User> userList = (List<User>) session.getAttribute("userList");
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
	<h1>ユーザー別最高スコアランキング</h1>
	<%if(userList != null){ %>
		<table class="ranking" border="1">
			<tr>
			  <th class="playHistory-cell">順位</th>
				<th class="playHistory-cell">名前</th>
				<th class="playHistory-cell">スコア</th>
			</tr>

			<%for(int i = 0; i < userList.size(); i++){%>
				<%if(userList.get(i).getName().equals(userName)){%>
					<tr class="userRecord">
					<% rank = i + 1; %>
				<%}else{ %>
					<tr>
				<%} %>
				<td class="playHistory-cell"><%= i+1 %></td>
				<td class="playHistory-cell"><%= userList.get(i).getName() %></td>
				<td class="playHistory-cell"><%= userList.get(i).getMaxScore()%></td>
				</tr>
			<%} %>
		</table>
		<%if(rank != 0){ %>
			<p>あなたの順位：<%= rank %>位<br>
		<%} %>

	<%}else{%>
		<p>プレイした人がいないため、ランキングがありません。</p>
	<%} %>

	<div class="ranking-adiv">
		<div class="ranking-eachDiv"><a class="ranking-a" href="/MyPage">マイページへ</a></div>
		<div class="ranking-eachDiv"><a class="ranking-a" href="/ranking.jsp">スコアTOP10ランキングへ</a></div>
		<div class="ranking-eachDiv"><a class="ranking-a" href="/allRanking.jsp">スコア全体ランキングへ</a><br></div>
	</div>
</body>
</html>