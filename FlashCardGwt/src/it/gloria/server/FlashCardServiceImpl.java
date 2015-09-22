package it.gloria.server;

import java.net.URL;

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
	public String createCard(FlashCard card)
			throws IllegalArgumentException {
		FlashCardDao dao = new FlashCardDao();
		return dao.createFile(card);
	}

	@Override
	public String getBlobstoreUploadUrl() {
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		String url = blobstoreService.createUploadUrl("/upload");
		System.out.print(url.replaceAll("ivantai", "localhost"));
		url = url.replaceAll("IvanTai", "localhost");
		return url ; 

	}

}
