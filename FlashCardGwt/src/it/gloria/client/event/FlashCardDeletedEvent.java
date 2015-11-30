package it.gloria.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class FlashCardDeletedEvent extends GwtEvent<FlashCardDeletedEventHandler> {
	public static Type<FlashCardDeletedEventHandler> TYPE = new Type<FlashCardDeletedEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashCardDeletedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashCardDeletedEventHandler handler) {
		handler.onFlashCardDeleted(this);
		
	}
	
}