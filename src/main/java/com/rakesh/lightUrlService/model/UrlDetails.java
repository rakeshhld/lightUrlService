package com.rakesh.lightUrlService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



/**
 * @author Rakesh
 * 
 * DB model classs
 *
 */
@Entity
public class UrlDetails {
	
	
	@Column
	private String shorturl;
	
	@Column
	private String longurl;
	
	@Id
	@Column()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String txId;
	
	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}
	public String getShorturl() {
		return shorturl;
	}
	public String getLongurl() {
		return longurl;
	}
	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}
	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	
	
	
	public UrlDetails(String shorturl, String longurl, String txId) {
		super();
		this.shorturl = shorturl;
		this.longurl = longurl;
		this.txId = txId;
	}
	/**
	 * @param longurl long url which is the actual url to be encoded
	 */
	public UrlDetails(String longurl) {
		this.longurl = longurl;
	}
	
	
	public UrlDetails() {}
	
	@Override
	public String toString() {
		return "UrlDetails [shorturl=" + shorturl + ", longurl=" + longurl + "]";
	}
	
	
	
	
	

}
