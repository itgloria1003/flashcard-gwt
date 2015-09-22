package it.gloria.client;

import it.gloria.shared.FlashCard;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FlashCardServiceAsync {

	void createCard(FlashCard card, AsyncCallback<String> callback);

	void getBlobstoreUploadUrl(AsyncCallback<String> callback);

}
