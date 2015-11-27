package it.gloria.server;

import it.gloria.client.FlashCardService;
import it.gloria.shared.FlashCard;

import java.util.ArrayList;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class FlashCardServiceImpl extends RemoteServiceServlet implements
		FlashCardService {
	
	FlashCardDao dao = new FlashCardDao();
	BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	ImagesService imageService = ImagesServiceFactory.getImagesService();
			
	
	@Override
	public String createCard(FlashCard card)
			throws IllegalArgumentException {
		
		return dao.createFile(card);
	}

	@Override
	public String getBlobstoreUploadUrl() {

		String url = blobstoreService.createUploadUrl("/upload");
		System.out.print(url.replaceAll("ivantai", "localhost"));
		url = url.replaceAll("IvanTai", "localhost");
		return url ; 

	}

	@Override
	public ArrayList<FlashCard> listFlashCards() {		
		ArrayList<FlashCard> rertieveFlashCards = dao.rertieveFlashCards();
		for (FlashCard card:rertieveFlashCards){
			ServingUrlOptions servingUrlOptions = ServingUrlOptions.Builder.withBlobKey(new BlobKey(card.getBlobKey()));
			servingUrlOptions.imageSize(600);
			String url = imageService.getServingUrl(servingUrlOptions);
			// special problem in local development 
			url = url.replaceAll("0.0.0.0", "localhost");
			card.setServingUrl(url);
//			card.setServingUrl("/serve?blobKey=" + card.getBlobKey());
		}
		return rertieveFlashCards;
	}

	@Override
	public String deleteCard(FlashCard card) {
		long i = dao.deleteByKey(card);
		return "deleted! " + i;
	}

	@Override
	public ArrayList<FlashCard> deleteFlashCards(ArrayList<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlashCard updateFlashCard(FlashCard flashCard) {
		// TODO Auto-generated method stub
		return null;
	}

}
