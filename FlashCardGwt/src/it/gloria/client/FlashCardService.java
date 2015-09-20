package it.gloria.client;

import it.gloria.shared.FlashCard;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("flashCard")
public interface FlashCardService extends RemoteService {
	String updateFlashCardDetails(FlashCard card) throws IllegalArgumentException;
	public String getBlobstoreUploadUrl();
}
