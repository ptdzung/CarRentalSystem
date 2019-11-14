/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Pham The Dzung
 */
@Entity
public class RentalRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalRecordId;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull
    private Date rentedOn;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull
    private Date rentedFrom;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull
    private Date rentedTo;
        
    @Column(nullable = false, length = 16)
    @NotNull
    @Size(min = 16, max = 16)
    private String creditCard;
    
    @Column(nullable = false, precision = 11, scale = 2)
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 9, fraction = 2)
    private BigDecimal totalAmount;
    
    @Column(nullable = false)
    @NotNull
    private Boolean cancelled;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date cancelledDate;
    
    //Entity relationship
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private CustomerEntity customer;
    
    @ManyToOne
    private PartnerEntity partner;
    
    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private OutletEntity pickupOutlet;
    
    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private OutletEntity returnOutlet;
    
    @OneToMany(mappedBy = "rentalRecord")
    private List<RentalDayEntity> rentalDays;
    
    @OneToOne
    private CarEntity car;
    
    @ManyToOne
    private CarModelEntity carModel;
    
    @ManyToOne
    private CarCategoryEntity carCategory;
    
    @OneToOne
    private TravelDispatchRecordEntity dispatchRecord;

    public RentalRecordEntity() {
        Calendar calendar = Calendar.getInstance();
        this.rentedOn = calendar.getTime();
        this.cancelled = false;
        
        rentalDays = new ArrayList<>();
    }

    //Own customer's rental
    public RentalRecordEntity(Date rentedFrom, Date rentedTo, String creditCard, OwnCustomerEntity customer, 
                              CarEntity car, CarModelEntity carModel, CarCategoryEntity carCategory) {
        this();
        
        this.rentedFrom = rentedFrom;
        this.rentedTo = rentedTo;
        this.creditCard = creditCard;
        this.customer = customer;
        this.car = car;
        this.carModel = carModel;
        this.carCategory = carCategory;
    }
    
    //Partner's rental
    public RentalRecordEntity(Date rentedFrom, Date rentedTo, String creditCard, PartnerEntity partner, CustomerEntity customer, 
                              CarEntity car, CarModelEntity carModel, CarCategoryEntity carCategory) {
        this();
        
        this.rentedFrom = rentedFrom;
        this.rentedTo = rentedTo;
        this.creditCard = creditCard;
        this.partner = partner;
        this.customer = customer;
        this.car = car;
        this.carModel = carModel;
        this.carCategory = carCategory;
    }
    
    
    public Long getRentalRecordId() {
        return rentalRecordId;
    }

    public void setRentalRecordId(Long rentalRecordId) {
        this.rentalRecordId = rentalRecordId;
    }

    public Date getRentedOn() {
        return rentedOn;
    }

    public void setRentedOn(Date rentedOn) {
        this.rentedOn = rentedOn;
    }

    public Date getRentedFrom() {
        return rentedFrom;
    }

    public void setRentedFrom(Date rentedFrom) {
        this.rentedFrom = rentedFrom;
    }

    public Date getRentedTo() {
        return rentedTo;
    }

    public void setRentedTo(Date rentedTo) {
        this.rentedTo = rentedTo;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public PartnerEntity getPartner() {
        return partner;
    }

    public void setPartner(PartnerEntity partner) {
        this.partner = partner;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public CarModelEntity getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModelEntity carModel) {
        this.carModel = carModel;
    }

    public CarCategoryEntity getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(CarCategoryEntity carCategory) {
        this.carCategory = carCategory;
    }

    public TravelDispatchRecordEntity getDispatchRecord() {
        return dispatchRecord;
    }

    public void setDispatchRecord(TravelDispatchRecordEntity dispatchRecord) {
        this.dispatchRecord = dispatchRecord;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Date getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(Date cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public OutletEntity getPickupOutlet() {
        return pickupOutlet;
    }

    public void setPickupOutlet(OutletEntity pickupOutlet) {
        this.pickupOutlet = pickupOutlet;
    }

    public OutletEntity getReturnOutlet() {
        return returnOutlet;
    }

    public void setReturnOutlet(OutletEntity returnOutlet) {
        this.returnOutlet = returnOutlet;
    }

    public List<RentalDayEntity> getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(List<RentalDayEntity> rentalDays) {
        this.rentalDays = rentalDays;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    
}
