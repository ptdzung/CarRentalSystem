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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import util.enumerator.AccessRightEnum;

/**
 *
 * @author Pham The Dzung
 */
@Entity
public class EmployeeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    
    @Column(nullable = false, length = 64)
    @NotNull
    @Size(max = 64)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessRightEnum accessRightEnum;
    
    @Column(nullable = false, unique = true, length = 32)
    @NotNull
    @Size(max = 32)
    private String username;
    
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    private String password;

    //Entity relationship
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private OutletEntity outlet;
    
    @OneToMany(mappedBy = "driver")
    private List<TravelDispatchRecordEntity> dispatchRecords;

    public EmployeeEntity() {
        dispatchRecords = new ArrayList<>();
    }

    public EmployeeEntity(String name, AccessRightEnum accessRightEnum, String username, String password, OutletEntity outlet) {
        this();
        
        this.name = name;
        this.accessRightEnum = accessRightEnum;
        this.username = username;
        this.password = password;
        this.outlet = outlet;
    }
    
    
    
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccessRightEnum getAccessRightEnum() {
        return accessRightEnum;
    }

    public void setAccessRightEnum(AccessRightEnum accessRightEnum) {
        this.accessRightEnum = accessRightEnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OutletEntity getOutlet() {
        return outlet;
    }

    public void setOutlet(OutletEntity outlet) {
        this.outlet = outlet;
    }

    public List<TravelDispatchRecordEntity> getDispatchRecords() {
        return dispatchRecords;
    }

    public void setDispatchRecords(ArrayList<TravelDispatchRecordEntity> dispatchRecords) {
        this.dispatchRecords = dispatchRecords;
    }

    
}
