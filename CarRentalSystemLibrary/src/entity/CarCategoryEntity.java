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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Pham The Dzung
 */
@Entity
public class CarCategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carCategoryId;

    @Column(nullable = false, unique = true, length = 32)
    @NotNull
    @Size(max = 32)
    private String categoryName;
    
    //Entity relationship
    @OneToMany(mappedBy = "carCategory")
    private List<RentalRateEntity> rentalRates;
    
    @OneToMany(mappedBy = "carCategory")
    private List<CarModelEntity> carModels;
    
    @OneToMany(mappedBy = "carCategory")
    private List<RentalRecordEntity> rentalRecords;

    
    public CarCategoryEntity() {
        rentalRates = new ArrayList<>();
        carModels = new ArrayList<>();
        rentalRecords = new ArrayList<>();
    }

    public CarCategoryEntity(String categoryName) {
        this();
        
        this.categoryName = categoryName;
    }
    
    
    public Long getCarCategoryId() {
        return carCategoryId;
    }

    public void setCarCategoryId(Long carCategoryId) {
        this.carCategoryId = carCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<RentalRateEntity> getRentalRates() {
        return rentalRates;
    }

    public void setRentalRates(ArrayList<RentalRateEntity> rentalRates) {
        this.rentalRates = rentalRates;
    }

    public List<CarModelEntity> getCarModels() {
        return carModels;
    }

    public void setCarModels(ArrayList<CarModelEntity> carModels) {
        this.carModels = carModels;
    }

    public List<RentalRecordEntity> getRentalRecords() {
        return rentalRecords;
    }

    public void setRentalRecord(List<RentalRecordEntity> rentalRecords) {
        this.rentalRecords = rentalRecords;
    }

    public void removeCarModel(CarModelEntity carModel) {
        carModels.remove(carModel);
    }
    
}
