package uy.com.geocom.dto;

import java.util.List;

public class MetadataNotificationDTO {

	private Long total;
	private List<NotificationQueryDTO> list;
	
	public MetadataNotificationDTO() {
		super();
	}
	
	public MetadataNotificationDTO(Long total, List<NotificationQueryDTO> list) {
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

	public List<NotificationQueryDTO> getList() {
		return list;
	}

	public void setList(List<NotificationQueryDTO> list) {
		this.list = list;
	}
	
}
