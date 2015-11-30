package it.gloria.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditFlashCardEvent extends GwtEvent<EditFlashCardEventHandler> {

	private Long idKey ;
	public static Type<EditFlashCardEventHandler> TYPE = new Type<EditFlashCardEventHandler>();

	public EditFlashCardEvent(Long idKey) {
		this.idKey = idKey ;
	}
	

	public Long getIdKey() {
		return idKey;
	}



	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditFlashCardEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditFlashCardEventHandler handler) {
		 handler.onEditFlashCard(this);

	}
	
}
	