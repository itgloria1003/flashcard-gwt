package it.gloria.shared;


public class FlashCardVerifier {

	public static ValidationMessage isEmpty(String desc, String fieldName) {
		ValidationMessage msg = new ValidationMessage();
		if (desc == null || ("".equals(desc.trim()))) {
			msg.setMessage(fieldName+ " cannot be empty");
			msg.setType(ValidationMessage.MessageType.ERROR);
		}
		return msg;
		
	}
}
