/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author This PC
 */
@Entity
@Table(name = "feedback")
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByFeedbackid", query = "SELECT f FROM Feedback f WHERE f.feedbackid = :feedbackid"),
    @NamedQuery(name = "Feedback.findByReview", query = "SELECT f FROM Feedback f WHERE f.review = :review"),
    @NamedQuery(name = "Feedback.findByDate", query = "SELECT f FROM Feedback f WHERE f.date = :date")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feedbackid")
    private Integer feedbackid;
    @Size(max = 500)
    @Column(name = "review")
    private String review;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "productid", referencedColumnName = "productid")
    @ManyToOne
    private Products productid;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne
    private Userregister email;

    public Feedback() {
    }

    public Feedback(Integer feedbackid) {
        this.feedbackid = feedbackid;
    }

    public Integer getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(Integer feedbackid) {
        this.feedbackid = feedbackid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Products getProductid() {
        return productid;
    }

    public void setProductid(Products productid) {
        this.productid = productid;
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
        hash += (feedbackid != null ? feedbackid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedbackid == null && other.feedbackid != null) || (this.feedbackid != null && !this.feedbackid.equals(other.feedbackid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Feedback[ feedbackid=" + feedbackid + " ]";
    }
    
}
