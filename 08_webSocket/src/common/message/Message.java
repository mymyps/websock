package common.message;

public class Message {

	private String userId;
	private String msg;
	private String room;
	private String flag;
	private String receiveId;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String userId, String msg, String room, String flag, String receiveId) {
		super();
		this.userId = userId;
		this.msg = msg;
		this.room = room;
		this.flag = flag;
		this.receiveId = receiveId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	@Override
	public String toString() {
		return "Message [userId=" + userId + ", msg=" + msg + ", room=" + room + ", flag=" + flag + ", receiveId="
				+ receiveId + "]";
	}
	
	
}
