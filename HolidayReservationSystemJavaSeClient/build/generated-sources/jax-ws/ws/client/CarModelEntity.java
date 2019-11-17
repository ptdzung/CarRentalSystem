
package ws.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for carModelEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="carModelEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="carCategory" type="{http://ws.session.ejb/}carCategoryEntity" minOccurs="0"/>
 *         &lt;element name="carModelId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="make" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentalRecords" type="{http://ws.session.ejb/}rentalRecordEntity" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "carModelEntity", propOrder = {
    "carCategory",
    "carModelId",
    "make",
    "modelName",
    "rentalRecords",
    "status"
})
public class CarModelEntity {

    protected CarCategoryEntity carCategory;
    protected Long carModelId;
    protected String make;
    protected String modelName;
    @XmlElement(nillable = true)
    protected List<RentalRecordEntity> rentalRecords;
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
     * Gets the value of the carModelId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCarModelId() {
        return carModelId;
    }

    /**
     * Sets the value of the carModelId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCarModelId(Long value) {
        this.carModelId = value;
    }

    /**
     * Gets the value of the make property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the value of the make property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMake(String value) {
        this.make = value;
    }

    /**
     * Gets the value of the modelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Sets the value of the modelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelName(String value) {
        this.modelName = value;
    }

    /**
     * Gets the value of the rentalRecords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rentalRecords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRentalRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RentalRecordEntity }
     * 
     * 
     */
    public List<RentalRecordEntity> getRentalRecords() {
        if (rentalRecords == null) {
            rentalRecords = new ArrayList<RentalRecordEntity>();
        }
        return this.rentalRecords;
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
