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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "userregister")
@NamedQueries({
    @NamedQuery(name = "Userregister.findAll", query = "SELECT u FROM Userregister u"),
    @NamedQuery(name = "Userregister.findByUsername", query = "SELECT u FROM Userregister u WHERE u.username = :username"),
    @NamedQuery(name = "Userregister.findByEmail", query = "SELECT u FROM Userregister u WHERE u.email = :email"),
    @NamedQuery(name = "Userregister.findByPassword", query = "SELECT u FROM Userregister u WHERE u.password = :password"),
    @NamedQuery(name = "Userregister.findByPhone", query = "SELECT u FROM Userregister u WHERE u.phone = :phone")})
public class Userregister implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private Integer phone;
    @OneToMany(mappedBy = "email")
    private Collection<Contactus> contactusCollection;
    @OneToMany(mappedBy = "email")
    private Collection<Feedback> feedbackCollection;
    @OneToMany(mappedBy = "email")
    private Collection<Addressmaster> addressmasterCollection;
    @JoinColumn(name = "groupid", referencedColumnName = "groupid")
    @ManyToOne
    private Groupmaster groupid;
    @OneToMany(mappedBy = "email")
    private Collection<Ordermaster> ordermasterCollection;
    @OneToMany(mappedBy = "email")
    private Collection<Payment> paymentCollection;
    @OneToMany(mappedBy = "userregister")
    private Collection<Cart> cartCollection;
    @OneToMany(mappedBy = "email")
    private Collection<Orderdetail> orderdetailCollection;

    public Userregister() {
    }

    public Userregister(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @JsonbTransient
    public Collection<Contactus> getContactusCollection() {
        return contactusCollection;
    }

    public void setContactusCollection(Collection<Contactus> contactusCollection) {
        this.contactusCollection = contactusCollection;
    }

    @JsonbTransient
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    @JsonbTransient
    public Collection<Addressmaster> getAddressmasterCollection() {
        return addressmasterCollection;
    }

    public void setAddressmasterCollection(Collection<Addressmaster> addressmasterCollection) {
        this.addressmasterCollection = addressmasterCollection;
    }

    public Groupmaster getGroupid() {
        return groupid;
    }

    public void setGroupid(Groupmaster groupid) {
        this.groupid = groupid;
    }
    
    @JsonbTransient
    public Collection<Ordermaster> getOrdermasterCollection() {
        return ordermasterCollection;
    }

    public void setOrdermasterCollection(Collection<Ordermaster> ordermasterCollection) {
        this.ordermasterCollection = ordermasterCollection;
    }

    @JsonbTransient
    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }

    @JsonbTransient
    public Collection<Cart> getCartCollection() {
        return cartCollection;
    }

    public void setCartCollection(Collection<Cart> cartCollection) {
        this.cartCollection = cartCollection;
    }

    @JsonbTransient
    public Collection<Orderdetail> getOrderdetailCollection() {
        return orderdetailCollection;
    }

    public void setOrderdetailCollection(Collection<Orderdetail> orderdetailCollection) {
        this.orderdetailCollection = orderdetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userregister)) {
            return false;
        }
        Userregister other = (Userregister) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Userregister[ email=" + email + " ]";
    }
    
}
