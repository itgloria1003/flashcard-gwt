package it.gloria.client;

import it.gloria.shared.FlashCard;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FlashCardServiceAsync {

	void createCard(FlashCard card, AsyncCallback<String> callback);
	

	void getBlobstoreUploadUrl(AsyncCallback<String> callback);

	void listFlashCards(AsyncCallback<ArrayList<FlashCard>> callback);
	


	void deleteCard(FlashCard card, AsyncCallback<String> callback);


	void deleteFlashCards(ArrayList<Long> ids,
			AsyncCallback<ArrayList<FlashCard>> asyncCallback);


	void updateFlashCard(FlashCard flashCard,
			AsyncCallback<FlashCard> asyncCallback);


	void getFlashCard(Long id, AsyncCallback<FlashCard> asyncCallback);


}
