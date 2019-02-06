package uy.com.geocom.dto;

import java.util.List;


public class NotificationResponseDTO {
	
	private String title;
	private String message;
	private Boolean enabled;
	private String validity;
	private Boolean read;
	private String user;
	private List<Integer> localIds; 
	List<Integer> channelIds;
	
	public NotificationResponseDTO() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Integer> getLocalIds() {
		return localIds;
	}

	public void setLocalIds(List<Integer> localIds) {
		this.localIds = localIds;
	}

	public List<Integer> getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(List<Integer> channelIds) {
		this.channelIds = channelIds;
	}
	
}
