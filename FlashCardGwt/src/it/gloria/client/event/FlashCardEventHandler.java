package it.gloria.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface FlashCardEventHandler extends EventHandler {
	  void onAddFlashCard(AddFlashCardEvent event);
	  void onDeleteFlashCard(DeleteFlashCardEvent event);
	  void onUpdateFlashCard(UpdateFlashCardEvent event);

}
