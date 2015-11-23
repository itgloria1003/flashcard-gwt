package it.gloria.shared;

import java.io.Serializable;

public class FlashCard  implements Serializable{
	private long idKey;


	public long getIdKey() {
		return idKey;
	}


	public void setIdKey(long idKey) {
		this.idKey = idKey;
	}

	private String chinCaption;
	private String engCaption;
	private String blobKey;
	private String servingUrl; 
	
	
	public String getServingUrl() {
		return servingUrl;
	}


	public void setServingUrl(String servingUrl) {
		this.servingUrl = servingUrl;
	}


	public String getChinCaption() {
		return chinCaption;
	}

	public void setChinCaption(String chinCaption) {
		this.chinCaption = chinCaption;
	}

	public String getEngCaption() {
		return engCaption;
	}

	public void setEngCaption(String engCaption) {
		this.engCaption = engCaption;
	}

	public String getBlobKey() {
		return blobKey;
	}

	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}




}