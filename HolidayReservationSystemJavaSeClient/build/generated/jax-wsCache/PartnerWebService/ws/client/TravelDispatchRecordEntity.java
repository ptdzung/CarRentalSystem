
package ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for travelDispatchRecordEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="travelDispatchRecordEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="driver" type="{http://ws.session.ejb/}employeeEntity" minOccurs="0"/>
 *         &lt;element name="receivingOutlet" type="{http://ws.session.ejb/}outletEntity" minOccurs="0"/>
 *         &lt;element name="rentalRecord" type="{http://ws.session.ejb/}rentalRecordEntity" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="travelDispatchRecordId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "travelDispatchRecordEntity", propOrder = {
    "driver",
    "receivingOutlet",
    "rentalRecord",
    "status",
    "travelDispatchRecordId"
})
public class TravelDispatchRecordEntity {

    protected EmployeeEntity driver;
    protected OutletEntity receivingOutlet;
    protected RentalRecordEntity rentalRecord;
    protected Boolean status;
    protected Long travelDispatchRecordId;

    /**
     * Gets the value of the driver property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeEntity }
     *     
     */
    public EmployeeEntity getDriver() {
        return driver;
    }

    /**
     * Sets the value of the driver property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeEntity }
     *     
     */
    public void setDriver(EmployeeEntity value) {
        this.driver = value;
    }

    /**
     * Gets the value of the receivingOutlet property.
     * 
     * @return
     *     possible object is
     *     {@link OutletEntity }
     *     
     */
    public OutletEntity getReceivingOutlet() {
        return receivingOutlet;
    }

    /**
     * Sets the value of the receivingOutlet property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutletEntity }
     *     
     */
    public void setReceivingOutlet(OutletEntity value) {
        this.receivingOutlet = value;
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

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStatus(Boolean value) {
        this.status = value;
    }

    /**
     * Gets the value of the travelDispatchRecordId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTravelDispatchRecordId() {
        return travelDispatchRecordId;
    }

    /**
     * Sets the value of the travelDispatchRecordId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTravelDispatchRecordId(Long value) {
        this.travelDispatchRecordId = value;
    }

}
