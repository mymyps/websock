package common.message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

// alt + s + v
public class MessageEncoder implements Encoder.Text<Message>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(Message object) throws EncodeException {
		// TODO Auto-generated method stub
//		return null;
		return new Gson().toJson(object);
	}

	
	
}
