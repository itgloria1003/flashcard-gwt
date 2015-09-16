package it.gloria.shared;


public class FlashCardVerifier {

	public static ValidationMessage validateEngDesc(String engDesc) {
		ValidationMessage msg = new ValidationMessage();
		if (engDesc == null || ("".equals(engDesc.trim()))) {
			msg.setMessage("Eng Caption cannot be empty");
			msg.setType(ValidationMessage.MessageType.ERROR);
		}
		return msg;
		
	}
}
