package uy.com.geocom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification_channel")
public class NotificationChannel {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_channel_id")
    private Integer notificationChannelId;
    @JoinColumn(name = "channel_id", referencedColumnName = "channel_id")
    @ManyToOne
    private Channel channel;
    @JoinColumn(name = "notification_id", referencedColumnName = "notification_id")
    @ManyToOne
    private Notification notification;
    
	public NotificationChannel() {

	}

	public Integer getNotificationChannelId() {
		return notificationChannelId;
	}

	public void setNotificationChannelId(Integer notificationChannelId) {
		this.notificationChannelId = notificationChannelId;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((notification == null) ? 0 : notification.hashCode());
		result = prime * result + ((notificationChannelId == null) ? 0 : notificationChannelId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationChannel other = (NotificationChannel) obj;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (notification == null) {
			if (other.notification != null)
				return false;
		} else if (!notification.equals(other.notification))
			return false;
		if (notificationChannelId == null) {
			if (other.notificationChannelId != null)
				return false;
		} else if (!notificationChannelId.equals(other.notificationChannelId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NotificationChannel [notificationChannelId=" + notificationChannelId + ", channel=" + channel
				+ ", notification=" + notification + "]";
	}

}
