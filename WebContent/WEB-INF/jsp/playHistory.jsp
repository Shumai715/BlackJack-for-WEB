<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList, model.PlayRecord" %>
<%
List<PlayRecord> recordList = (List<PlayRecord>)request.getAttribute("recordList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<h1>プレイ履歴</h1>
<%if(recordList.size() == 0 || recordList == null){ %>
	<p>プレイ履歴がありません。</p>
<%}else{%>
	<table class="playHistory" border="1">
		<tr>
		  <th class="playHistory-cell">日付</th>
		  <th class="playHistory-cell">スコア</th>
		</tr>
		<%for(PlayRecord record: recordList){ %>
			<tr>
			  <td class="playHistory-cell"><%= record.getDate() %></td>
			  <td class="playHistory-cell"><%= record.getScore() %></td>
			</tr>
		<%} %>
	</table>
<%} %>

<a href="/MyPage">マイページへ</a>
</body>
</html>