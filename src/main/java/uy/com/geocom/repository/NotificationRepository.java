package uy.com.geocom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uy.com.geocom.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID>{
	
	@Query("select n from Notification n join fetch n.notificationLocalList join fetch n.notificationChannelList where n.notificationId = :id")
	Notification findNotificationById(@Param("id") UUID id);
	
	@Query("select n from Notification n")
	Page<Notification> findNotificationWithQuery(Pageable page);

}
