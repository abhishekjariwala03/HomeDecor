/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author This PC
 */
@Entity
@Table(name = "groupmaster")
@NamedQueries({
    @NamedQuery(name = "Groupmaster.findAll", query = "SELECT g FROM Groupmaster g"),
    @NamedQuery(name = "Groupmaster.findByGroupid", query = "SELECT g FROM Groupmaster g WHERE g.groupid = :groupid"),
    @NamedQuery(name = "Groupmaster.findByGroupname", query = "SELECT g FROM Groupmaster g WHERE g.groupname = :groupname")})
public class Groupmaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "groupid")
    private Integer groupid;
    @Size(max = 50)
    @Column(name = "groupname")
    private String groupname;
    @OneToMany(mappedBy = "groupid")
    private Collection<Userregister> userregisterCollection;

    public Groupmaster() {
    }

    public Groupmaster(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @JsonbTransient
    public Collection<Userregister> getUserregisterCollection() {
        return userregisterCollection;
    }

    public void setUserregisterCollection(Collection<Userregister> userregisterCollection) {
        this.userregisterCollection = userregisterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupid != null ? groupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupmaster)) {
            return false;
        }
        Groupmaster other = (Groupmaster) object;
        if ((this.groupid == null && other.groupid != null) || (this.groupid != null && !this.groupid.equals(other.groupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Groupmaster[ groupid=" + groupid + " ]";
    }
    
}
