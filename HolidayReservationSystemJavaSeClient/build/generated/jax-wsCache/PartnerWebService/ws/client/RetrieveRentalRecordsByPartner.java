
package ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retrieveRentalRecordsByPartner complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="retrieveRentalRecordsByPartner">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="part" type="{http://ws.session.ejb/}partnerEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveRentalRecordsByPartner", propOrder = {
    "part"
})
public class RetrieveRentalRecordsByPartner {

    protected PartnerEntity part;

    /**
     * Gets the value of the part property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerEntity }
     *     
     */
    public PartnerEntity getPart() {
        return part;
    }

    /**
     * Sets the value of the part property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerEntity }
     *     
     */
    public void setPart(PartnerEntity value) {
        this.part = value;
    }

}
