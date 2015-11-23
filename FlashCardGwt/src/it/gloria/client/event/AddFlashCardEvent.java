package it.gloria.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddFlashCardEvent extends GwtEvent<FlashCardEventHandler> {
	public static Type<FlashCardEventHandler> TYPE = new Type<FlashCardEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashCardEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashCardEventHandler handler) {
		 handler.onAddFlashCard(this);

	}
}
