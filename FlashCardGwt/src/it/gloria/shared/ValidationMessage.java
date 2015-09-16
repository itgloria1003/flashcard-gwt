package it.gloria.shared;

public class ValidationMessage {
	public enum MessageType {
		ERROR, INFO
	}
	private MessageType type;
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}