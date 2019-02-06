package uy.com.geocom.dto;

import java.util.List;

import uy.com.geocom.model.Notification;

public class MetadataNotificationEntityDTO {

	private Long total;
	private List<Notification> list;
	
	public MetadataNotificationEntityDTO() {
		super();
	}

	public MetadataNotificationEntityDTO(Long total, List<Notification> list) {
		super();
		this.total = total;
		this.list = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<Notification> getList() {
		return list;
	}

	public void setList(List<Notification> list) {
		this.list = list;
	}
	
}
