/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import util.enumerator.StatusEnum;

/**
 *
 * @author Pham The Dzung
 */
@Entity
public class CarModelEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carModelId;
    
    @Column(nullable = false, length = 128)
    @NotNull
    @Size(max = 128)
    private String modelName;
    
    @Column(nullable = false, length = 64)
    @NotNull
    @Size(max = 64)
    private String make;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private StatusEnum status;
    
    //Entity relationship
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private CarCategoryEntity carCategory;
    
    @OneToMany(mappedBy = "carModel")
    private List<CarEntity> cars;
    
    @OneToMany(mappedBy = "carModel")
    private List<RentalRecordEntity> rentalRecords;
    
    
    public CarModelEntity() {
        status = StatusEnum.AVAILABLE;
        cars = new ArrayList<>();
        rentalRecords = new ArrayList<>();
    }

    public CarModelEntity(String modelName, String make, CarCategoryEntity carCategory) {
        this();
        
        this.modelName = modelName;
        this.make = make;
        this.carCategory = carCategory;
    }
    
    

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
    
    public CarCategoryEntity getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(CarCategoryEntity carCategory) {
        this.carCategory = carCategory;
    }

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(ArrayList<CarEntity> cars) {
        this.cars = cars;
    }

    public List<RentalRecordEntity> getRentalRecords() {
        return rentalRecords;
    }

    public void setRentalRecords(List<RentalRecordEntity> rentalRecords) {
        this.rentalRecords = rentalRecords;
    }
    
    public void removeCar(CarEntity car) {
        cars.remove(car);
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
