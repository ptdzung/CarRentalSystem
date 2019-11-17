
package ws.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rentalRecordEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rentalRecordEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cancelled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="cancelledDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="car" type="{http://ws.session.ejb/}carEntity" minOccurs="0"/>
 *         &lt;element name="carCategory" type="{http://ws.session.ejb/}carCategoryEntity" minOccurs="0"/>
 *         &lt;element name="carModel" type="{http://ws.session.ejb/}carModelEntity" minOccurs="0"/>
 *         &lt;element name="creditCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customer" type="{http://ws.session.ejb/}customerEntity" minOccurs="0"/>
 *         &lt;element name="dispatchRecord" type="{http://ws.session.ejb/}travelDispatchRecordEntity" minOccurs="0"/>
 *         &lt;element name="hasPaid" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pickupOutlet" type="{http://ws.session.ejb/}outletEntity" minOccurs="0"/>
 *         &lt;element name="rentalDays" type="{http://ws.session.ejb/}rentalDayEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rentalRecordId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="rentedFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="rentedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="rentedTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="returnOutlet" type="{http://ws.session.ejb/}outletEntity" minOccurs="0"/>
 *         &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rentalRecordEntity", propOrder = {
    "cancelled",
    "cancelledDate",
    "car",
    "carCategory",
    "carModel",
    "creditCardNumber",
    "customer",
    "dispatchRecord",
    "hasPaid",
    "pickupOutlet",
    "rentalDays",
    "rentalRecordId",
    "rentedFrom",
    "rentedOn",
    "rentedTo",
    "returnOutlet",
    "totalAmount"
})
public class RentalRecordEntity {

    protected Boolean cancelled;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cancelledDate;
    protected CarEntity car;
    protected CarCategoryEntity carCategory;
    protected CarModelEntity carModel;
    protected String creditCardNumber;
    protected CustomerEntity customer;
    protected TravelDispatchRecordEntity dispatchRecord;
    protected boolean hasPaid;
    protected OutletEntity pickupOutlet;
    @XmlElement(nillable = true)
    protected List<RentalDayEntity> rentalDays;
    protected Long rentalRecordId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rentedFrom;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rentedOn;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rentedTo;
    protected OutletEntity returnOutlet;
    protected BigDecimal totalAmount;

    /**
     * Gets the value of the cancelled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the value of the cancelled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCancelled(Boolean value) {
        this.cancelled = value;
    }

    /**
     * Gets the value of the cancelledDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCancelledDate() {
        return cancelledDate;
    }

    /**
     * Sets the value of the cancelledDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCancelledDate(XMLGregorianCalendar value) {
        this.cancelledDate = value;
    }

    /**
     * Gets the value of the car property.
     * 
     * @return
     *     possible object is
     *     {@link CarEntity }
     *     
     */
    public CarEntity getCar() {
        return car;
    }

    /**
     * Sets the value of the car property.
     * 
     * @param value
     *     allowed object is
     *     {@link CarEntity }
     *     
     */
    public void setCar(CarEntity value) {
        this.car = value;
    }

    /**
     * Gets the value of the carCategory property.
     * 
     * @return
     *     possible object is
     *     {@link CarCategoryEntity }
     *     
     */
    public CarCategoryEntity getCarCategory() {
        return carCategory;
    }

    /**
     * Sets the value of the carCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link CarCategoryEntity }
     *     
     */
    public void setCarCategory(CarCategoryEntity value) {
        this.carCategory = value;
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
     * Gets the value of the creditCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * Sets the value of the creditCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardNumber(String value) {
        this.creditCardNumber = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerEntity }
     *     
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerEntity }
     *     
     */
    public void setCustomer(CustomerEntity value) {
        this.customer = value;
    }

    /**
     * Gets the value of the dispatchRecord property.
     * 
     * @return
     *     possible object is
     *     {@link TravelDispatchRecordEntity }
     *     
     */
    public TravelDispatchRecordEntity getDispatchRecord() {
        return dispatchRecord;
    }

    /**
     * Sets the value of the dispatchRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link TravelDispatchRecordEntity }
     *     
     */
    public void setDispatchRecord(TravelDispatchRecordEntity value) {
        this.dispatchRecord = value;
    }

    /**
     * Gets the value of the hasPaid property.
     * 
     */
    public boolean isHasPaid() {
        return hasPaid;
    }

    /**
     * Sets the value of the hasPaid property.
     * 
     */
    public void setHasPaid(boolean value) {
        this.hasPaid = value;
    }

    /**
     * Gets the value of the pickupOutlet property.
     * 
     * @return
     *     possible object is
     *     {@link OutletEntity }
     *     
     */
    public OutletEntity getPickupOutlet() {
        return pickupOutlet;
    }

    /**
     * Sets the value of the pickupOutlet property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutletEntity }
     *     
     */
    public void setPickupOutlet(OutletEntity value) {
        this.pickupOutlet = value;
    }

    /**
     * Gets the value of the rentalDays property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rentalDays property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRentalDays().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RentalDayEntity }
     * 
     * 
     */
    public List<RentalDayEntity> getRentalDays() {
        if (rentalDays == null) {
            rentalDays = new ArrayList<RentalDayEntity>();
        }
        return this.rentalDays;
    }

    /**
     * Gets the value of the rentalRecordId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRentalRecordId() {
        return rentalRecordId;
    }

    /**
     * Sets the value of the rentalRecordId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRentalRecordId(Long value) {
        this.rentalRecordId = value;
    }

    /**
     * Gets the value of the rentedFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRentedFrom() {
        return rentedFrom;
    }

    /**
     * Sets the value of the rentedFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRentedFrom(XMLGregorianCalendar value) {
        this.rentedFrom = value;
    }

    /**
     * Gets the value of the rentedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRentedOn() {
        return rentedOn;
    }

    /**
     * Sets the value of the rentedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRentedOn(XMLGregorianCalendar value) {
        this.rentedOn = value;
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
     * Gets the value of the returnOutlet property.
     * 
     * @return
     *     possible object is
     *     {@link OutletEntity }
     *     
     */
    public OutletEntity getReturnOutlet() {
        return returnOutlet;
    }

    /**
     * Sets the value of the returnOutlet property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutletEntity }
     *     
     */
    public void setReturnOutlet(OutletEntity value) {
        this.returnOutlet = value;
    }

    /**
     * Gets the value of the totalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmount(BigDecimal value) {
        this.totalAmount = value;
    }

}
