package uy.com.geocom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uy.com.geocom.model.Channel;
import uy.com.geocom.model.Notification;
import uy.com.geocom.model.NotificationChannel;

@Repository
public interface NotificationChannelRepository extends JpaRepository<NotificationChannel, Integer>{

	NotificationChannel findByChannelAndNotification(Channel channel, Notification notification);
}
