package it.gloria.client.event;

import it.gloria.shared.FlashCard;

import com.google.gwt.event.shared.GwtEvent;

public class FlashCardUpdatedEvent extends GwtEvent<FlashCardUpdatedEventHandler> {
	private FlashCard card ;
	
	public FlashCardUpdatedEvent(FlashCard card){
		this.card = card;
	}
	
	public FlashCard getCard() {
		return card;
	}

	public static Type<FlashCardUpdatedEventHandler> TYPE = new Type<FlashCardUpdatedEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashCardUpdatedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashCardUpdatedEventHandler handler) {
		 handler.onFlashCardUpdated(this);
	}
}
