package uy.com.geocom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uy.com.geocom.model.Local;
import uy.com.geocom.model.Notification;
import uy.com.geocom.model.NotificationLocal;

@Repository
public interface NotificationLocalRepository extends JpaRepository<NotificationLocal, Integer>, NotificationRepositoryCustom {

	NotificationLocal findByLocalAndNotification(Local local, Notification notification);
}
