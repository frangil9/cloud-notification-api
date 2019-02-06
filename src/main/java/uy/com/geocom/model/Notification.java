/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.geocom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "notification")
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name= "notification_id", columnDefinition = "BINARY(16)")
    private UUID notificationId;
    @Column(name = "title")
    private String title;
    @Column(name = "message")
    private String message;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "validity")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validity;
    @Column(name = "reading")
    private Boolean read;
    @Column(name = "user")
    private String user;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notification")
    @JsonIgnore
    private Set<NotificationChannel> notificationChannelList;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notification")
    @JsonIgnore
    private Set<NotificationLocal> notificationLocalList;

    public Notification() {
    }

    public UUID getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(UUID notificationId) {
		this.notificationId = notificationId;
	}
	
	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }  

	public Set<NotificationChannel> getNotificationChannelList() {
		return notificationChannelList;
	}

	public void setNotificationChannelList(Set<NotificationChannel> notificationChannelList) {
		this.notificationChannelList = notificationChannelList;
	}

	public Set<NotificationLocal> getNotificationLocalList() {
        return notificationLocalList;
    }

    public void setNotificationLocalList(Set<NotificationLocal> notificationLocalList) {
        this.notificationLocalList = notificationLocalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationId != null ? notificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notificationId == null && other.notificationId != null) || (this.notificationId != null && !this.notificationId.equals(other.notificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.Notification[ notificationId=" + notificationId + " ]";
    }
    
}
