/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.geocom.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification_local")
public class NotificationLocal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_local_id")
    private Integer notificationLocalId;
    @JoinColumn(name = "local_id", referencedColumnName = "local_id")
    @ManyToOne
    private Local local;
    @JoinColumn(name = "notification_id", referencedColumnName = "notification_id")
    @ManyToOne
    private Notification notification;

    public NotificationLocal() {
    }

    public NotificationLocal(Integer notificationLocalId) {
        this.notificationLocalId = notificationLocalId;
    }

    public Integer getNotificationLocalId() {
        return notificationLocalId;
    }

    public void setNotificationLocalId(Integer notificationLocalId) {
        this.notificationLocalId = notificationLocalId;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationLocalId != null ? notificationLocalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationLocal)) {
            return false;
        }
        NotificationLocal other = (NotificationLocal) object;
        if ((this.notificationLocalId == null && other.notificationLocalId != null) || (this.notificationLocalId != null && !this.notificationLocalId.equals(other.notificationLocalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.NotificationLocal[ notificationLocalId=" + notificationLocalId + " ]";
    }
    
}
