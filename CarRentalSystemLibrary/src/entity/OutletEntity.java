/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Pham The Dzung
 */
@Entity
public class OutletEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outletId;
    
    @Column(nullable = false, unique = true, length = 128)
    @NotNull
    @Size(max = 128)
    private String name;
    
    @Column
    @Temporal(TemporalType.TIME)
    private Date openTime;
    
    @Column
    @Temporal(TemporalType.TIME)
    private Date closeTime;
    
    //Entity relationship
    @OneToMany(mappedBy = "outlet")
    private List<CarEntity> cars;
    
    @OneToMany(mappedBy = "outlet")
    private List<EmployeeEntity> employees;
    
    @OneToMany(mappedBy = "receivingOutlet")
    private List<TravelDispatchRecordEntity> dispatchRecords;

    
    public OutletEntity() {
        cars = new ArrayList<>();
        employees = new ArrayList<>();
        dispatchRecords = new ArrayList<>();
    }

    public OutletEntity(String name) {
        this();
        
        this.name = name;
    }
    
    public OutletEntity(String name, Date openTime, Date closeTime) {
        this();
        
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
    

    public Long getOutletId() {
        return outletId;
    }

    public void setOutletId(Long outletId) {
        this.outletId = outletId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(ArrayList<CarEntity> cars) {
        this.cars = cars;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public List<TravelDispatchRecordEntity> getDispatchRecords() {
        return dispatchRecords;
    }

    public void setDispatchRecords(ArrayList<TravelDispatchRecordEntity> dispatchRecords) {
        this.dispatchRecords = dispatchRecords;
    }
    
    
}
