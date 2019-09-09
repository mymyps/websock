<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>chatting</title>
</head>
<body>
	<div id="result">
		<input type="text" id="message" />
		<button id="btn-send">전송</button>
	</div>
	
	<script type="text/javascript">
		//webSocket create
		// var socket = new WebSocket('ws://192.168.20.238:9090/chatting');
		var socket = new WebSocket('ws://localhost:8081/<%=request.getContextPath()%>/chatting');
		socket.onopen = function (e) {
			console.log(e);
		}
		socket.onmessage = function (e) {
			console.log(e);
			var p = $('<p>').html(e.data);
			$('#result').append(p);
		}
		socket.onclose
		
		$(function(){
			$('#btn-send').click(function () {
				// server에 데이터 전송
				socket.send($('#message').val());
			});
		});

	</script>

</body>
</html>