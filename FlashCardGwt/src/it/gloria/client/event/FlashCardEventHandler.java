package it.gloria.client.event;

import com.google.gwt.event.shared.EventHandler;

// this is the event bus 
public interface FlashCardEventHandler extends EventHandler {
	void onAddFlashCard(AddFlashCardEvent event);

	void onFlashCardDeleted(FlashCardDeletedEvent event);

	void onFlashCardUpdated(FlashCardUpdatedEvent event);

	void onEditFlashCard(EditFlashCardEvent editFlashCardEvent);

}
