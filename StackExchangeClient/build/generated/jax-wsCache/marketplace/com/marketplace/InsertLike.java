
package com.marketplace;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for insertLike complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="insertLike"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="barangid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="userid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertLike", propOrder = {
    "barangid",
    "userid"
})
public class InsertLike {

    protected int barangid;
    protected int userid;

    /**
     * Gets the value of the barangid property.
     * 
     */
    public int getBarangid() {
        return barangid;
    }

    /**
     * Sets the value of the barangid property.
     * 
     */
    public void setBarangid(int value) {
        this.barangid = value;
    }

    /**
     * Gets the value of the userid property.
     * 
     */
    public int getUserid() {
        return userid;
    }

    /**
     * Sets the value of the userid property.
     * 
     */
    public void setUserid(int value) {
        this.userid = value;
    }

}
