
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
 * <p>Java class for rentalRateEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rentalRateEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="carCategory" type="{http://ws.session.ejb/}carCategoryEntity" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="rateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ratePerDay" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="rentalDays" type="{http://ws.session.ejb/}rentalDayEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rentalRateId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
@XmlType(name = "rentalRateEntity", propOrder = {
    "carCategory",
    "endDate",
    "rateName",
    "ratePerDay",
    "rentalDays",
    "rentalRateId",
    "startDate",
    "status"
})
public class RentalRateEntity {

    protected CarCategoryEntity carCategory;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected String rateName;
    protected BigDecimal ratePerDay;
    @XmlElement(nillable = true)
    protected List<RentalDayEntity> rentalDays;
    protected Long rentalRateId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    protected StatusEnum status;

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
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the rateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateName() {
        return rateName;
    }

    /**
     * Sets the value of the rateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateName(String value) {
        this.rateName = value;
    }

    /**
     * Gets the value of the ratePerDay property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRatePerDay() {
        return ratePerDay;
    }

    /**
     * Sets the value of the ratePerDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRatePerDay(BigDecimal value) {
        this.ratePerDay = value;
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
     * Gets the value of the rentalRateId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRentalRateId() {
        return rentalRateId;
    }

    /**
     * Sets the value of the rentalRateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRentalRateId(Long value) {
        this.rentalRateId = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
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
