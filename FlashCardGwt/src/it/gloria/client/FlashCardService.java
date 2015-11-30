package it.gloria.client;

import java.util.ArrayList;

import it.gloria.shared.FlashCard;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("flashCard")
public interface FlashCardService extends RemoteService {
	String createCard(FlashCard card) throws IllegalArgumentException;
	public String getBlobstoreUploadUrl();
	public ArrayList<FlashCard> listFlashCards();
	String deleteCard(FlashCard card);
	ArrayList<FlashCard> deleteFlashCards(ArrayList<Long> ids);
	FlashCard updateFlashCard(FlashCard flashCard);
	FlashCard getFlashCard(Long id);
	
}
