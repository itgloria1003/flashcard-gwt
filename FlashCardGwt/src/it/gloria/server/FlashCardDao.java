package it.gloria.server;

import it.gloria.shared.FlashCard;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;


public class FlashCardDao{
	
	private DatastoreService datastoreService = DatastoreServiceFactory
			.getDatastoreService();
	
	
	public Entity toEntity(FlashCard card) {
		Key k = KeyFactory.createKey(FlashCard.FLASH_CARD, card.getEngCaption());
		Entity en = new Entity(k);
		en.setProperty("chinCaption", card.getChinCaption());
		en.setProperty("engCaption", card.getEngCaption());
		en.setProperty("blobKey", card.getBlobKey());

		return en;
	}

	public static FlashCard fromEntity(Entity en) {
		FlashCard c = new FlashCard();
		c.setBlobKey((String) en.getProperty("blobKey"));
		c.setChinCaption((String) en.getProperty("chinCaption"));
		c.setEngCaption((String) en.getProperty("engCaption"));
		return c;

	}
	public String createFile(FlashCard card){
			// save the record
			Transaction beginTransaction = datastoreService.beginTransaction();
			Key put = datastoreService.put(beginTransaction, toEntity(card));
			beginTransaction.commit();
			return put.getAppId();
			
	}
	
	
}