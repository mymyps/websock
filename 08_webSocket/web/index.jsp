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
	<input type="text" id="userId" placeholder="아이디" />
	<input type="text" id="receiveId" placeholder="아이디" />
	<div id="members"></div>
	<div id="result">
		<input type="text" id="message" />
		<button id="btn-send">전송</button>
		<input type="file" id='up_file' />
		<button id='upload'>파일업로드</button>
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
			console.log(typeof e.data);
			//var d = e.data.split(',');
			
			var d = JSON.parse(e.data);
			
			console.log(typeof d);
			console.log(d);
			
			console.log(d['flag']);
			//if(d[d.length - 1] == "members"){
				//var p = $('<p>').html(e.data[1]);
				//$('#members').append(p);
			//	$('#members').html(d.data[1]);
			//}
				
			//var p = $('<p>').html(e.data);
			//$('#result').append(p);
		}
		//socket.onclose
		
		$(function(){
			
			// 파일 업로드
			$('#upload').click(function () {
				var file = $('#up_file')[0].files[0];
				// 파일을 전송한다는 의미의 타입을 설정
				socket.binaryType = 'arraybuffer';
				var reader = new FileReader();
				var rawData = new ArrayBuffer();
				
				reader.onload = function (e) {
					rawData = e.target.result;
					socket.send(rawData);
				}
				reader.readAsArrayBuffer(file);
				
			});
			
			
			
			//
			$('#btn-send').click(function () {
				// server에 데이터 전송
				//socket.send($('#message').val());
				//socket.send('user01,' + $('#message').val() + ',01,msg');
				//socket.send($('#userId').val() + ',' + $('#message').val() + ',01,msg,' + $('#receiveId').val());
				
				var msg = {
						'userId' : $('#userId').val(),
						'msg' : $('#message').val(),
						'room' : '01',
						'flag' : 'msg',
						'receiveId' : ''
				};
				//console.log(msg);
				socket.send(JSON.stringify(msg));
			});
		});

	</script>

</body>
</html>