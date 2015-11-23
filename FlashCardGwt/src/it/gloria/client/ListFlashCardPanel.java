package it.gloria.client;

import it.gloria.shared.FlashCard;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Image;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.constants.BackdropType;
import com.google.code.gwt.crop.client.GWTCropper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ListFlashCardPanel extends Composite {

	private static ListFlashCardPanelUiBinder uiBinder = GWT
			.create(ListFlashCardPanelUiBinder.class);

	interface ListFlashCardPanelUiBinder extends
			UiBinder<Widget, ListFlashCardPanel> {
	}

	FlashCardGwt flashCardGwt; 
	
	@UiField
	Image image;
	@UiField
	Heading engCaption;
	@UiField
	Heading chinCaption;

	@UiField
	Button deleteButton;

	FlashCard flashCard;
	
	FlashCardServiceAsync flashCardService; 

	public ListFlashCardPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	
	
	public void init(FlashCardServiceAsync service,
			FlashCard card) { 
		image.setUrl(card.getServingUrl());
		image.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				GWTCropper cropper = new GWTCropper(image.getUrl());
//				GWTCropper cropper = new GWTCropper("http://w32blaster.github.io/gwt-cropper/images/wiki/gwt-cropper-magnifier.jpg");
				Modal modal = new Modal();	
				modal.setTitle("Edit Image");
				modal.setAnimation(true);
				modal.setWidth(700);
				modal.setBackdrop(BackdropType.STATIC);
				modal.add(cropper);
				modal.show();
				
				
			}
		});
		engCaption.setText(card.getEngCaption());
		chinCaption.setText(card.getChinCaption());
		this.flashCard = card;
		this.flashCardService = service;
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
//				Window.alert("card:"+  flashCard.getIdKey());
				flashCardService.deleteCard(flashCard, new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						flashCardGwt.refresh();
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

			}
		});
	}

}
