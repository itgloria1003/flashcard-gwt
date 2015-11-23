package it.gloria.server;

import it.gloria.shared.FlashCard;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.google.appengine.api.datastore.Transaction;


public class FlashCardDao{
	
	private DatastoreService datastoreService = DatastoreServiceFactory
			.getDatastoreService();
	public static final String FLASH_CARD = "FlashCard";
	
	
	public Entity toEntity(FlashCard card) {
		Entity en = new Entity(FLASH_CARD);
		en.setProperty("chinCaption", card.getChinCaption());
		en.setProperty("engCaption", card.getEngCaption());
		en.setProperty("blobKey", card.getBlobKey());

		return en;
	}

	public static FlashCard fromEntity(Entity en) {
		FlashCard c = new FlashCard();
		c.setIdKey(en.getKey().getId());		
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
	public long deleteByKey(FlashCard card){
		Key key = KeyFactory.createKey(FlashCardDao.FLASH_CARD, card.getIdKey());
		Transaction beginTransaction = datastoreService.beginTransaction();
		datastoreService.delete(beginTransaction,key);
		beginTransaction.commit();
		return card.getIdKey();
	}

	public ArrayList<FlashCard> rertieveFlashCards() {

		ArrayList<FlashCard> cardList = new ArrayList<FlashCard>();  
		Query query = new Query(FlashCardDao.FLASH_CARD);
		query.addSort("blobKey");
		PreparedQuery prepare = datastoreService.prepare(query);
		QueryResultIterable<Entity> asQueryResultIterable = prepare.asQueryResultIterable();
		for (Entity en: asQueryResultIterable){
			cardList.add(FlashCardDao.fromEntity(en));
		}
		return cardList;
			
	}
	
	
}