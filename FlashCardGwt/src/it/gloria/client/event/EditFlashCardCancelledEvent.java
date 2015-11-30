package it.gloria.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditFlashCardCancelledEvent extends GwtEvent<EditFlashCardCancelledEventHandler> {
	public static Type<EditFlashCardCancelledEventHandler> TYPE = new Type<EditFlashCardCancelledEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditFlashCardCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditFlashCardCancelledEventHandler handler) {
		 handler.onEditFlashCardCancelled(this);

	}
}
