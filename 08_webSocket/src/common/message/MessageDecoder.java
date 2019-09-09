package common.message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class MessageDecoder implements Decoder.Text<Message>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message decode(String s) throws DecodeException {
		// TODO Auto-generated method stub
//		return null;
		return new Gson().fromJson(s, Message.class);
		
	}

	@Override
	public boolean willDecode(String s) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
