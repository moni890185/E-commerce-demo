package com.project.monica.snobsinenobilitate.models.pojo;

public class RequestMeta {

	private int id;
  private String url;
	private String eTag;
	private long maxAge;
	private String locale;
	private String data;
	private String requestType;

	public RequestMeta(int id, String url, String eTag, long maxAge, String locale, String data, String requestType) {
		this.id = id;
		this.url = url;
		this.eTag = eTag;
		this.maxAge = maxAge;
		this.locale = locale;
		this.data = data;
		this.requestType = requestType;
	}

	public RequestMeta(String url) {
		this.url = url;
		eTag = "";
	}

	public int getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String geteTag() {
		return eTag;
	}

	public long getMaxAge() {
		return maxAge;
	}

	public String getLocale() {
		return locale;
	}

	public String getData() {
		return data;
	}

	public String getRequestType() {
		return requestType;
	}
}