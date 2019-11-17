
package ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for carEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="carEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="carId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="carModel" type="{http://ws.session.ejb/}carModelEntity" minOccurs="0"/>
 *         &lt;element name="licensePlate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outlet" type="{http://ws.session.ejb/}outletEntity" minOccurs="0"/>
 *         &lt;element name="rentalRecord" type="{http://ws.session.ejb/}rentalRecordEntity" minOccurs="0"/>
 *         &lt;element name="rentedTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://ws.session.ejb/}statusEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carEntity", propOrder = {
    "carId",
    "carModel",
    "licensePlate",
    "outlet",
    "rentalRecord",
    "rentedTo",
    "status"
})
public class CarEntity {

    protected Long carId;
    protected CarModelEntity carModel;
    protected String licensePlate;
    protected OutletEntity outlet;
    protected RentalRecordEntity rentalRecord;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rentedTo;
    protected StatusEnum status;

    /**
     * Gets the value of the carId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCarId() {
        return carId;
    }

    /**
     * Sets the value of the carId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCarId(Long value) {
        this.carId = value;
    }

    /**
     * Gets the value of the carModel property.
     * 
     * @return
     *     possible object is
     *     {@link CarModelEntity }
     *     
     */
    public CarModelEntity getCarModel() {
        return carModel;
    }

    /**
     * Sets the value of the carModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link CarModelEntity }
     *     
     */
    public void setCarModel(CarModelEntity value) {
        this.carModel = value;
    }

    /**
     * Gets the value of the licensePlate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Sets the value of the licensePlate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicensePlate(String value) {
        this.licensePlate = value;
    }

    /**
     * Gets the value of the outlet property.
     * 
     * @return
     *     possible object is
     *     {@link OutletEntity }
     *     
     */
    public OutletEntity getOutlet() {
        return outlet;
    }

    /**
     * Sets the value of the outlet property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutletEntity }
     *     
     */
    public void setOutlet(OutletEntity value) {
        this.outlet = value;
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
     * Gets the value of the rentedTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRentedTo() {
        return rentedTo;
    }

    /**
     * Sets the value of the rentedTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRentedTo(XMLGregorianCalendar value) {
        this.rentedTo = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusEnum }
     *     
     */
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusEnum }
     *     
     */
    public void setStatus(StatusEnum value) {
        this.status = value;
    }

}
