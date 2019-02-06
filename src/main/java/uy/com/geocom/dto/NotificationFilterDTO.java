package uy.com.geocom.dto;

import java.util.List;

public class NotificationFilterDTO {
	
	private List<Integer> locals;
	private List<Integer> channels;
	private Boolean enabled;
	private Integer page;
	private Integer max;
	private String order;
	private String field;
	
	public NotificationFilterDTO() {
		super();
	}

	public NotificationFilterDTO(List<Integer> locals, List<Integer> channels, Boolean enabled, Integer page,
			Integer max, String order, String field) {
		super();
		this.locals = locals;
		this.channels = channels;
		this.enabled = enabled;
		this.page = page;
		this.max = max;
		this.order = order;
		this.field = field;
	}

	public List<Integer> getLocals() {
		return locals;
	}

	public void setLocals(List<Integer> locals) {
		this.locals = locals;
	}

	public List<Integer> getChannels() {
		return channels;
	}

	public void setChannels(List<Integer> channels) {
		this.channels = channels;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
