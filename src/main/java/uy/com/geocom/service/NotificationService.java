package uy.com.geocom.service;

import java.util.List;
import java.util.UUID;

import uy.com.geocom.dto.MetadataNotificationDTO;
import uy.com.geocom.dto.MetadataNotificationEntityDTO;
import uy.com.geocom.dto.NotificationDTO;
import uy.com.geocom.dto.NotificationResponseDTO;
import uy.com.geocom.exception.GeocomRESTException;
import uy.com.geocom.model.Notification;

public interface NotificationService {
	
	public Notification save(NotificationDTO dto) throws GeocomRESTException;
	
	public Notification update(UUID id, NotificationDTO dto) throws GeocomRESTException;
	
	public NotificationResponseDTO findById(UUID id) throws GeocomRESTException;
	
	public MetadataNotificationDTO findAll(List<Integer> locals, List<Integer> channels, Boolean enabled, Integer page, Integer max, String order, String field);
	
	public MetadataNotificationEntityDTO findNotificationWithQuery(Integer page, Integer max, String order, String field);

}
