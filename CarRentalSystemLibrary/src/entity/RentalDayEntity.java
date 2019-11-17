/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Pham The Dzung
 */
@Entity
public class RentalDayEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalDayId;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull
    private Date rentalDay;
    
    //Entity relationship
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private RentalRateEntity rentalRate;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private RentalRecordEntity rentalRecord;

    public RentalDayEntity() {
    }

    public RentalDayEntity(Date rentalDay, RentalRateEntity rentalRate, RentalRecordEntity rentalRecord) {
        this.rentalDay = rentalDay;
        this.rentalRate = rentalRate;
        this.rentalRecord = rentalRecord;
    }
    

    public Long getRentalDayId() {
        return rentalDayId;
    }

    public void setRentalDayId(Long rentalDayId) {
        this.rentalDayId = rentalDayId;
    }

    public Date getRentalDay() {
        return rentalDay;
    }

    public void setRentalDay(Date rentalDay) {
        this.rentalDay = rentalDay;
    }

    public RentalRateEntity getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(RentalRateEntity rentalRate) {
        this.rentalRate = rentalRate;
    }

    public RentalRecordEntity getRentalRecord() {
        return rentalRecord;
    }

    public void setRentalRecord(RentalRecordEntity rentalRecord) {
        this.rentalRecord = rentalRecord;
    }

    
}