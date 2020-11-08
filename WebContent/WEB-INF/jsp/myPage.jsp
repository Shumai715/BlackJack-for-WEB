<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
int playCount = (int)request.getAttribute("playCount");
int maxScore = (int)request.getAttribute("maxScore");

String userName = (String)session.getAttribute("userName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body class="myPage-body">
<h1>My Page</h1><br>
<div class="container">
	<div class="mainBox mypageBox">
	<div class="rule">
		<h2>・ルール</h2>
		<p>手札の数字の合計がディーラーより21に近ければあなたの勝ちです。<br>
		ただし、21を超えてしまうとバーストとなり、負けになるので注意してください。</p>
		<p>また、ディーラーは手札の合計が17以上になるまで引きます。<br>
		どちらもバーストした場合、プレイヤーの負けとなります。</p>
		<p>・数字の数え方<br>
		　1　… 1 or 11<br>
		2～10 … 数字通り<br>
		J～K  … 10</p>

		<p>・配当<br>
		通常時→1倍<br>
		ブラックジャック時→1.5倍<br>
		（ブラックジャック ＝ 初手で21）</p><br>
		<p>注意：このゲームは1デックで行います。<br>
		山札のカードを全て引き終わった回が最後の勝負です。<br>
		なお、山札のカードが無くなった後は、捨て札からランダムで引かれます。</p>
		</div>
		<div class="myPage-button">
			<form action="/PlayStart" method="get">
				<input type="submit" value="ゲーム開始!">
			</form>

			<form action="/Ranking" method="get">
				<input type="submit" value="ランキングを見る">
			</form>
		</div>
	</div>

	<div class="side-bar mypageBox">
		<div class="profile border">名前：<%= userName %></div>
		<div class="playCount border">プレイ回数: <%= playCount %></div>
		<div class="maxScore border">最高スコア：<%if(playCount == 0){ %>なし<%}else{%> <%= maxScore %> <%} %></div>
		<div class="history"><a href="/PlayHistory">プレイ履歴</a></div>
		<div class="logOut"><a href="/LogOut">ログアウト</a></div>
	</div>
</div>
</body>
</html>