package com.websocket.controller;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import common.message.Message;
import common.message.MessageDecoder;
import common.message.MessageEncoder;

@ServerEndpoint(value = "/chatting",
		encoders = {MessageEncoder.class},
		decoders = {MessageDecoder.class}
)
public class ChattingServer {
	
	// 클라이언트가 웹소켓 서버에 연결요청 
	@OnOpen
	public void open(Session session, EndpointConfig config) {
		System.out.println("접속 성공! / " + session.getId());
	}
	
	@OnMessage
	public void msg(Session ss, Message msg) {
		System.out.println(msg); 
		try {
			ss.getBasicRemote().sendObject( new Message("admin","접속을환영", "01", "msg", "") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	@OnMessage
	public void msg(Session ss, String mg) {
		System.out.println("msg : " + mg);

		// 메시지 파싱
		String[] msgs = mg.split(",");
		for (String s : msgs) {
			System.out.print(s + " / ");
		}
		
		//세션에 필요한 데이터 넣기
		ss.getUserProperties().put("userId", msgs[0]);
		ss.getUserProperties().put("msg", msgs[1]);
		ss.getUserProperties().put("room", msgs[2]);
		ss.getUserProperties().put("flag", msgs[3]);
		ss.getUserProperties().put("receiveId", msgs[4]);
		
		//세션객체에 연결되어있는 세션을 확인할 수 있는 메소드지원
		// 클라에서 받은 메시지를 프론트로 다시 전송
		try {
			
			// 현재 연결되어 있는 전체 세션을 가져올 수 있음
			for (Session s : ss.getOpenSessions()) {
				String userId = (String)s.getUserProperties().get("userId");
				String receiveId = (String)s.getUserProperties().get("receiveId");
				
				//전체 접속회원에게 보내기
				sendMember(ss);
		
//				s.getBasicRemote().sendText("admin," + userId + ",01,members");
				
				if(userId != null && receiveId != null && userId.equals(receiveId)) {
//				if(userId != null && userId.equals("user01")) {
//					s.getBasicRemote().sendText(userId + " : " + mg);
					s.getBasicRemote().sendText(userId + "," + mg + ",01,msg");
				}
				
				if(receiveId == null) {
					s.getBasicRemote().sendText(userId + "," + mg + ",01,msg");
				}
				
				s.getBasicRemote().sendText("관리자 : " + mg);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/

	private void sendMember(Session ss) {
		
		String members = "";
		for (Session s : ss.getOpenSessions()) {
			members = (String)s.getUserProperties().get("userId") + "§";
			
		}
		
		try {
			ss.getBasicRemote().sendText("admin," + members + ",01,members");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@OnMessage(
			maxMessageSize = 1024 * 1024 * 10)
	public void message(ByteBuffer data, Session ss) {
		
		System.out.println("data trans");
		System.out.println(data + " / " + ss);
		
		try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:\\file"));) {
			bos.write(data.array());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	
	
	

}
