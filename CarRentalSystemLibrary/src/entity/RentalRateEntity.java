/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import util.enumerator.StatusEnum;

/**
 *
 * @author Pham The Dzung
 */
@Entity
public class RentalRateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalRateId;
    
    @Column(nullable = false, length = 64)
    @NotNull
    @Size(max = 64)
    private String rateName;
    
    @Column(nullable = false, precision = 11, scale = 2)
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 9, fraction = 2)
    private BigDecimal ratePerDay;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private StatusEnum status;
    
    //Entity relationship
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private CarCategoryEntity carCategory;
    
    @OneToMany(mappedBy = "rentalRate")
    private List<RentalDayEntity> rentalDays;

    public RentalRateEntity() {
        status = StatusEnum.AVAILABLE;
        rentalDays = new ArrayList<>();
    }

    public RentalRateEntity(String rateName, BigDecimal ratePerDay, Date startDate, Date endDate, CarCategoryEntity carCategory) {
        this();
        
        this.rateName = rateName;
        this.ratePerDay = ratePerDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.carCategory = carCategory;
    }


    
    public Long getRentalRateId() {
        return rentalRateId;
    }

    public void setRentalRateId(Long rentalRateId) {
        this.rentalRateId = rentalRateId;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public BigDecimal getRatePerDay() {
        return ratePerDay;
    }

    public void setRatePerDay(BigDecimal ratePerDay) {
        this.ratePerDay = ratePerDay;
    }
//
//    public RateTypeEnum getRateType() {
//        return rateType;
//    }
//
//    public void setRateType(RateTypeEnum rateType) {
//        this.rateType = rateType;
//    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CarCategoryEntity getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(CarCategoryEntity carCategory) {
        this.carCategory = carCategory;
    }

    public List<RentalDayEntity> getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(List<RentalDayEntity> rentalDays) {
        this.rentalDays = rentalDays;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
