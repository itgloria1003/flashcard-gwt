package it.gloria.client.event;

import it.gloria.shared.FlashCard;

import com.google.gwt.event.shared.GwtEvent;

public class FlashCardUpdatedEvent extends GwtEvent<FlashCardEventHandler> {
	private FlashCard card ;
	
	FlashCardUpdatedEvent(FlashCard card){
		this.card = card;
	}
	
	public FlashCard getCard() {
		return card;
	}

	public static Type<FlashCardEventHandler> TYPE = new Type<FlashCardEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashCardEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashCardEventHandler handler) {
		 handler.onFlashCardUpdated(this);

	}
}
