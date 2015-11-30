package it.gloria.client.event;

import com.google.gwt.event.shared.EventHandler;

// this is the event bus 
public interface EditFlashCardEventHandler extends EventHandler {
	void onEditFlashCard(EditFlashCardEvent editFlashCardEvent);

}
