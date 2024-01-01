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
import javax.persistence.JoinColumns;
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
@Table(name = "orderdetail")
@NamedQueries({
    @NamedQuery(name = "Orderdetail.findAll", query = "SELECT o FROM Orderdetail o"),
    @NamedQuery(name = "Orderdetail.findByDetailid", query = "SELECT o FROM Orderdetail o WHERE o.detailid = :detailid"),
    @NamedQuery(name = "Orderdetail.findByProductName", query = "SELECT o FROM Orderdetail o WHERE o.productName = :productName"),
    @NamedQuery(name = "Orderdetail.findByRate", query = "SELECT o FROM Orderdetail o WHERE o.rate = :rate"),
    @NamedQuery(name = "Orderdetail.findByQty", query = "SELECT o FROM Orderdetail o WHERE o.qty = :qty"),
    @NamedQuery(name = "Orderdetail.findByAmount", query = "SELECT o FROM Orderdetail o WHERE o.amount = :amount"),
    @NamedQuery(name = "Orderdetail.findByTax", query = "SELECT o FROM Orderdetail o WHERE o.tax = :tax"),
    @NamedQuery(name = "Orderdetail.findByGst", query = "SELECT o FROM Orderdetail o WHERE o.gst = :gst")})
public class Orderdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detailid")
    private Integer detailid;
    @Size(max = 255)
    @Column(name = "productName")
    private String productName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rate")
    private Double rate;
    @Column(name = "qty")
    private Integer qty;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "tax")
    private Double tax;
    @Column(name = "gst")
    private Double gst;
    @JoinColumns({
        @JoinColumn(name = "orderid", referencedColumnName = "orderid"),
        @JoinColumn(name = "orderid", referencedColumnName = "orderid")})
    @ManyToOne
    private Ordermaster ordermaster;
    @JoinColumn(name = "productid", referencedColumnName = "productid")
    @ManyToOne
    private Products productid;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne
    private Userregister email;

    public Orderdetail() {
    }

    public Orderdetail(Integer detailid) {
        this.detailid = detailid;
    }

    public Integer getDetailid() {
        return detailid;
    }

    public void setDetailid(Integer detailid) {
        this.detailid = detailid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    public Ordermaster getOrdermaster() {
        return ordermaster;
    }

    public void setOrdermaster(Ordermaster ordermaster) {
        this.ordermaster = ordermaster;
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
        hash += (detailid != null ? detailid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderdetail)) {
            return false;
        }
        Orderdetail other = (Orderdetail) object;
        if ((this.detailid == null && other.detailid != null) || (this.detailid != null && !this.detailid.equals(other.detailid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Orderdetail[ detailid=" + detailid + " ]";
    }
    
}
