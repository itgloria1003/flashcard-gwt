package it.gloria.server;

import it.gloria.shared.FlashCard;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


public class FlashCardDao{

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
}