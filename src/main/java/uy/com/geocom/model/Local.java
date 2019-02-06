/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.geocom.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "local")
public class Local implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_id")
    private Integer localId;
    @Column(name = "local")
    private int local;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "local")
    @JsonIgnore
    private List<NotificationLocal> notificationLocalList;

    public Local() {
    }

    public Local(Integer localId) {
        this.localId = localId;
    }

    public Local(Integer localId, int local) {
        this.localId = localId;
        this.local = local;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public List<NotificationLocal> getNotificationLocalList() {
        return notificationLocalList;
    }

    public void setNotificationLocalList(List<NotificationLocal> notificationLocalList) {
        this.notificationLocalList = notificationLocalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localId != null ? localId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.localId == null && other.localId != null) || (this.localId != null && !this.localId.equals(other.localId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.Local[ localId=" + localId + " ]";
    }
    
}
