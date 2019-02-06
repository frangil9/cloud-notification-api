package uy.com.geocom.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NotificationDTO {

	@NotNull(message="El titulo no puede ser nulo")
	@Size(min=2, message="El titulo debe tener minimo 2 caracteres")
	private String title;
	@NotNull(message="El mensaje no puede ser nulo")
	@Size(min=2, message="El mensaje debe tener minimo 2 caracteres")
	private String message;
	@NotNull(message="El campo habilitado no puede ser nulo")
	private Boolean enabled;
	@NotNull(message="La fecha de validez no puede ser nulo")
	private String validity;
	private Boolean read;
	//@NotNull(message="El usuario no puede ser nulo")
	private String user;
	@NotEmpty (message="La lista de locale no puede estar vacia")
	private List<Integer> localIds; 
	@NotEmpty (message="La lista de canales no puede estar vacia")
	private List<Integer> channelIds;

	public NotificationDTO() {

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

	public Boolean isEnabled() {
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

	public Boolean isRead() {
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
