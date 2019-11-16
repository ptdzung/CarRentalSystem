/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Pham The Dzung
 */
@Entity
public class TravelDispatchRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelDispatchRecordId;
    
    @Column(nullable = false)
    @NotNull
    private Boolean status;
    
    //Entity relationship
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private OutletEntity receivingOutlet;
    
    @ManyToOne
    private EmployeeEntity driver;
    
    @OneToOne(mappedBy = "dispatchRecord")
    private RentalRecordEntity rentalRecord;

    public TravelDispatchRecordEntity() {
        this.status = false;
    }

    public TravelDispatchRecordEntity(OutletEntity receivingOutlet, RentalRecordEntity rentalRecord) {
        this();
        
        this.receivingOutlet = receivingOutlet;
        this.rentalRecord = rentalRecord;
    }
    
    

    public Long getTravelDispatchRecordId() {
        return travelDispatchRecordId;
    }

    public void setTravelDispatchRecordId(Long travelDispatchRecordId) {
        this.travelDispatchRecordId = travelDispatchRecordId;
    }

    public OutletEntity getReceivingOutlet() {
        return receivingOutlet;
    }

    public void setReceivingOutlet(OutletEntity receivingOutlet) {
        this.receivingOutlet = receivingOutlet;
    }

    public EmployeeEntity getDriver() {
        return driver;
    }

    public void setDriver(EmployeeEntity driver) {
        this.driver = driver;
    }

    public RentalRecordEntity getRentalRecord() {
        return rentalRecord;
    }

    public void setRentalRecord(RentalRecordEntity rentalRecord) {
        this.rentalRecord = rentalRecord;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    
}
