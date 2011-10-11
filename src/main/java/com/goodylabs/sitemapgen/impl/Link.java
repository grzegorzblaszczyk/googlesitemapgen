package com.goodylabs.sitemapgen.impl;

import java.util.Date;

import com.goodylabs.sitemapgen.impl.enums.ChangeFreq;

public class Link {
	
	private String uri;
	private Date lastModified;
	private Double priority;
	private ChangeFreq changeFreq;
	
	public Link(String uri, Date lastModified, Double priority, ChangeFreq changeFreq) {
		this.uri = uri;
		this.lastModified = lastModified;
		this.priority = priority;
		this.changeFreq = changeFreq;
		
		if (this.priority != null && (priority < 0.0 || priority > 1)) {
			throw new RuntimeException("Incorrect value of priority: " + priority);
		}
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public Double getPriority() {
		return priority;
	}
	public void setPriority(Double priority) {
		this.priority = priority;
	}
	public ChangeFreq getChangeFreq() {
		return changeFreq;
	}
	public void setChangeFreq(ChangeFreq changeFreq) {
		this.changeFreq = changeFreq;
	}
	
	
}
