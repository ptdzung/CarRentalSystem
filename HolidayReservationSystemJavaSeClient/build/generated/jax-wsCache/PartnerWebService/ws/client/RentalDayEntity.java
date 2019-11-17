
package ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rentalDayEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rentalDayEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rentalDay" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="rentalDayId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="rentalRate" type="{http://ws.session.ejb/}rentalRateEntity" minOccurs="0"/>
 *         &lt;element name="rentalRecord" type="{http://ws.session.ejb/}rentalRecordEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rentalDayEntity", propOrder = {
    "rentalDay",
    "rentalDayId",
    "rentalRate",
    "rentalRecord"
})
public class RentalDayEntity {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rentalDay;
    protected Long rentalDayId;
    protected RentalRateEntity rentalRate;
    protected RentalRecordEntity rentalRecord;

    /**
     * Gets the value of the rentalDay property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRentalDay() {
        return rentalDay;
    }

    /**
     * Sets the value of the rentalDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRentalDay(XMLGregorianCalendar value) {
        this.rentalDay = value;
    }

    /**
     * Gets the value of the rentalDayId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRentalDayId() {
        return rentalDayId;
    }

    /**
     * Sets the value of the rentalDayId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRentalDayId(Long value) {
        this.rentalDayId = value;
    }

    /**
     * Gets the value of the rentalRate property.
     * 
     * @return
     *     possible object is
     *     {@link RentalRateEntity }
     *     
     */
    public RentalRateEntity getRentalRate() {
        return rentalRate;
    }

    /**
     * Sets the value of the rentalRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link RentalRateEntity }
     *     
     */
    public void setRentalRate(RentalRateEntity value) {
        this.rentalRate = value;
    }

    /**
     * Gets the value of the rentalRecord property.
     * 
     * @return
     *     possible object is
     *     {@link RentalRecordEntity }
     *     
     */
    public RentalRecordEntity getRentalRecord() {
        return rentalRecord;
    }

    /**
     * Sets the value of the rentalRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link RentalRecordEntity }
     *     
     */
    public void setRentalRecord(RentalRecordEntity value) {
        this.rentalRecord = value;
    }

}
