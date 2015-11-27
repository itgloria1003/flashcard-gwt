package it.gloria.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditFlashCardEvent extends GwtEvent<FlashCardEventHandler> {

	private Long idKey ;
	public static Type<FlashCardEventHandler> TYPE = new Type<FlashCardEventHandler>();

	public EditFlashCardEvent(Long id) {
		this.idKey = id ;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashCardEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashCardEventHandler handler) {
		 handler.onEditFlashCard(this);

	}
	
}
