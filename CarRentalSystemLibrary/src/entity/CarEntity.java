/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import util.enumerator.StatusEnum;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Pham The Dzung
 */
@Entity
public class CarEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    
    @Column(nullable = false, unique = true, length = 8)
    @NotNull
    @Size(min = 8, max = 8)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private StatusEnum status;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentedTo;
    
    //Entity relationship
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private CarModelEntity carModel;
    
    @ManyToOne
    private OutletEntity outlet;
    
    @OneToOne(mappedBy = "car")
    private RentalRecordEntity rentalRecord;
    
    
    public CarEntity() {
    }

    public CarEntity(String licensePlate, CarModelEntity carModel, StatusEnum status, OutletEntity outlet) {
        this();
        
        this.licensePlate = licensePlate;
        this.carModel = carModel;
        this.status = status;
        this.outlet = outlet;
    }


    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public CarModelEntity getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModelEntity carModel) {
        this.carModel = carModel;
    }

    public Date getRentedTo() {
        return rentedTo;
    }

    public void setRentedTo(Date rentedTo) {
        this.rentedTo = rentedTo;
    }
    
    public RentalRecordEntity getRentalRecord() {
        return rentalRecord;
    }

    public void setRentalRecord(RentalRecordEntity rentalRecord) {
        this.rentalRecord = rentalRecord;
    }

    public OutletEntity getOutlet() {
        return outlet;
    }

    public void setOutlet(OutletEntity outlet) {
        this.outlet = outlet;
    }
}
