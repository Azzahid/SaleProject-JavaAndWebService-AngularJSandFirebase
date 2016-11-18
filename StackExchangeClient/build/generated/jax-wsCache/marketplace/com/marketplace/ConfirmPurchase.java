
package com.marketplace;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for confirmPurchase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="confirmPurchase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="buyer_id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="product_id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="consignee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fulladdress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="creditcardnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="postalcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="phonenumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="card_verification" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmPurchase", propOrder = {
    "buyerId",
    "productId",
    "consignee",
    "fulladdress",
    "quantity",
    "creditcardnumber",
    "postalcode",
    "phonenumber",
    "cardVerification"
})
public class ConfirmPurchase {

    @XmlElement(name = "buyer_id")
    protected int buyerId;
    @XmlElement(name = "product_id")
    protected int productId;
    protected String consignee;
    protected String fulladdress;
    protected int quantity;
    protected String creditcardnumber;
    protected String postalcode;
    protected String phonenumber;
    @XmlElement(name = "card_verification")
    protected String cardVerification;

    /**
     * Gets the value of the buyerId property.
     * 
     */
    public int getBuyerId() {
        return buyerId;
    }

    /**
     * Sets the value of the buyerId property.
     * 
     */
    public void setBuyerId(int value) {
        this.buyerId = value;
    }

    /**
     * Gets the value of the productId property.
     * 
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     */
    public void setProductId(int value) {
        this.productId = value;
    }

    /**
     * Gets the value of the consignee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * Sets the value of the consignee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsignee(String value) {
        this.consignee = value;
    }

    /**
     * Gets the value of the fulladdress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFulladdress() {
        return fulladdress;
    }

    /**
     * Sets the value of the fulladdress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFulladdress(String value) {
        this.fulladdress = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the creditcardnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditcardnumber() {
        return creditcardnumber;
    }

    /**
     * Sets the value of the creditcardnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditcardnumber(String value) {
        this.creditcardnumber = value;
    }

    /**
     * Gets the value of the postalcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * Sets the value of the postalcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalcode(String value) {
        this.postalcode = value;
    }

    /**
     * Gets the value of the phonenumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * Sets the value of the phonenumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhonenumber(String value) {
        this.phonenumber = value;
    }

    /**
     * Gets the value of the cardVerification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardVerification() {
        return cardVerification;
    }

    /**
     * Sets the value of the cardVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardVerification(String value) {
        this.cardVerification = value;
    }

}
