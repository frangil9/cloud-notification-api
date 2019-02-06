package uy.com.geocom.repository;

import java.util.List;

import uy.com.geocom.dto.MetadataNotificationDTO;
import uy.com.geocom.dto.NotificationQueryDTO;

public interface NotificationRepositoryCustom {

	public MetadataNotificationDTO findBylocalOrChannel(List<Integer> locals, List<Integer> channels, Boolean enabled, Integer page, Integer max, String order, String field);
}
