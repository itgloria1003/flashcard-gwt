package it.gloria.server;

import it.gloria.client.FlashCardService;
import it.gloria.shared.FlashCard;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class FlashCardServiceImpl extends RemoteServiceServlet implements
		FlashCardService {

	@Override
	public String updateFlashCardDetails(FlashCard card)
			throws IllegalArgumentException {
		FlashCardDao dao = new FlashCardDao();
		return dao.update(card);
	}

	@Override
	public String getBlobstoreUploadUrl() {
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		return blobstoreService.createUploadUrl("/upload");

	}

}
