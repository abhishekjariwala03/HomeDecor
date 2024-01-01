/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author This PC
 */
@Entity
@Table(name = "addressmaster")
@NamedQueries({
    @NamedQuery(name = "Addressmaster.findAll", query = "SELECT a FROM Addressmaster a"),
    @NamedQuery(name = "Addressmaster.findByAddressid", query = "SELECT a FROM Addressmaster a WHERE a.addressid = :addressid"),
    @NamedQuery(name = "Addressmaster.findByCity", query = "SELECT a FROM Addressmaster a WHERE a.city = :city"),
    @NamedQuery(name = "Addressmaster.findByState", query = "SELECT a FROM Addressmaster a WHERE a.state = :state"),
    @NamedQuery(name = "Addressmaster.findByPincode", query = "SELECT a FROM Addressmaster a WHERE a.pincode = :pincode")})
public class Addressmaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "addressid")
    private Integer addressid;
    @Size(max = 50)
    @Column(name = "city")
    private String city;
    @Size(max = 50)
    @Column(name = "state")
    private String state;
    @Column(name = "pincode")
    private Integer pincode;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne
    private Userregister email;

    public Addressmaster() {
    }

    public Addressmaster(Integer addressid) {
        this.addressid = addressid;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Userregister getEmail() {
        return email;
    }

    public void setEmail(Userregister email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressid != null ? addressid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Addressmaster)) {
            return false;
        }
        Addressmaster other = (Addressmaster) object;
        if ((this.addressid == null && other.addressid != null) || (this.addressid != null && !this.addressid.equals(other.addressid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Addressmaster[ addressid=" + addressid + " ]";
    }
    
}
