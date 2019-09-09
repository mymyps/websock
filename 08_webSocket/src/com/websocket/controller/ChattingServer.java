package com.websocket.controller;

import java.io.IOException;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatting")
public class ChattingServer {
	
	// 클라이언트가 웹소켓 서버에 연결요청 
	@OnOpen
	public void open(Session session, EndpointConfig config) {
		System.out.println("접속 성공! / " + session.getId());
	}
	
	@OnMessage
	public void msg(Session ss, String mg) {
		System.out.println("msg : " + mg);
		
		// 클라에서 받은 메시지를 프론트로 다시 전송
		try {
			
			// 현재 연결되어 있는 전체 세션을 가져올 수 있음
			for (Session s : ss.getOpenSessions()) {
				s.getBasicRemote().sendText("관리자 : " + mg);
			} 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
