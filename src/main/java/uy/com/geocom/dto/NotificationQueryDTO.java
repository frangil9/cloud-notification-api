package uy.com.geocom.dto;

import java.util.Date;
import java.util.UUID;

public class NotificationQueryDTO {
	
	private UUID id;
	private String title;
	private String message;
	private Date validity;
	private Integer local;
	private boolean enabled;
	private String channel;
	
	public NotificationQueryDTO() {
		super();
	}

	public NotificationQueryDTO(UUID id, String title, String message, Date validity, Integer local,
			boolean enabled, String channel) {
		super();
		this.id = id;
		this.title = title;
		this.message = message;
		this.validity = validity;
		this.local = local;
		this.enabled = enabled;
		this.channel = channel;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
