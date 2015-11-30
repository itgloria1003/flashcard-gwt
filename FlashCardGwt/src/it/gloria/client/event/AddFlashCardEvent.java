package it.gloria.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddFlashCardEvent extends GwtEvent<AddFlashCardEventHandler> {
	public static Type<AddFlashCardEventHandler> TYPE = new Type<AddFlashCardEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AddFlashCardEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddFlashCardEventHandler handler) {
		 handler.onAddFlashCard(this);

	}
}
