/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marketplace;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Silva
 */
@Entity
@Table(name = "purchase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p"),
    @NamedQuery(name = "Purchase.findByPurchaseId", query = "SELECT p FROM Purchase p WHERE p.purchaseId = :purchaseId"),
    @NamedQuery(name = "Purchase.findByProductId", query = "SELECT p FROM Purchase p WHERE p.productId = :productId"),
    @NamedQuery(name = "Purchase.findByConsignee", query = "SELECT p FROM Purchase p WHERE p.consignee = :consignee"),
    @NamedQuery(name = "Purchase.findByFulladdress", query = "SELECT p FROM Purchase p WHERE p.fulladdress = :fulladdress"),
    @NamedQuery(name = "Purchase.findByQuantity", query = "SELECT p FROM Purchase p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Purchase.findByCreditcardnumber", query = "SELECT p FROM Purchase p WHERE p.creditcardnumber = :creditcardnumber"),
    @NamedQuery(name = "Purchase.findByPostalcode", query = "SELECT p FROM Purchase p WHERE p.postalcode = :postalcode"),
    @NamedQuery(name = "Purchase.findByPhonenumber", query = "SELECT p FROM Purchase p WHERE p.phonenumber = :phonenumber"),
    @NamedQuery(name = "Purchase.findByCreatedAt", query = "SELECT p FROM Purchase p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "Purchase.findByCardVerification", query = "SELECT p FROM Purchase p WHERE p.cardVerification = :cardVerification"),
    @NamedQuery(name = "Purchase.findByProductName", query = "SELECT p FROM Purchase p WHERE p.productName = :productName"),
    @NamedQuery(name = "Purchase.findByProductDescription", query = "SELECT p FROM Purchase p WHERE p.productDescription = :productDescription"),
    @NamedQuery(name = "Purchase.findByProductPrice", query = "SELECT p FROM Purchase p WHERE p.productPrice = :productPrice"),
    @NamedQuery(name = "Purchase.findByImageType", query = "SELECT p FROM Purchase p WHERE p.imageType = :imageType")})
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "purchase_id")
    private Integer purchaseId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_id")
    private int productId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "consignee")
    private String consignee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fulladdress")
    private String fulladdress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "creditcardnumber")
    private String creditcardnumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "postalcode")
    private String postalcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "phonenumber")
    private String phonenumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "card_verification")
    private String cardVerification;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "product_name")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "product_description")
    private String productDescription;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "product_price")
    private String productPrice;
    @Lob
    @Column(name = "product_photourl")
    private byte[] productPhotourl;
    @Size(max = 25)
    @Column(name = "image_type")
    private String imageType;
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Integer buyerId;
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Integer sellerId;

    public Purchase() {
    }

    public Purchase(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Purchase(Integer purchaseId, int productId, String consignee, String fulladdress, int quantity, String creditcardnumber, String postalcode, String phonenumber, Date createdAt, String cardVerification, String productName, String productDescription, String productPrice) {
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.consignee = consignee;
        this.fulladdress = fulladdress;
        this.quantity = quantity;
        this.creditcardnumber = creditcardnumber;
        this.postalcode = postalcode;
        this.phonenumber = phonenumber;
        this.createdAt = createdAt;
        this.cardVerification = cardVerification;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getFulladdress() {
        return fulladdress;
    }

    public void setFulladdress(String fulladdress) {
        this.fulladdress = fulladdress;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreditcardnumber() {
        return creditcardnumber;
    }

    public void setCreditcardnumber(String creditcardnumber) {
        this.creditcardnumber = creditcardnumber;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCardVerification() {
        return cardVerification;
    }

    public void setCardVerification(String cardVerification) {
        this.cardVerification = cardVerification;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public byte[] getProductPhotourl() {
        return productPhotourl;
    }

    public void setProductPhotourl(byte[] productPhotourl) {
        this.productPhotourl = productPhotourl;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseId != null ? purchaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.purchaseId == null && other.purchaseId != null) || (this.purchaseId != null && !this.purchaseId.equals(other.purchaseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.marketplace.Purchase[ purchaseId=" + purchaseId + " ]";
    }
    
}
