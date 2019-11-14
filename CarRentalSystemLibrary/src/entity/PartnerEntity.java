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
public class PartnerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerId;
    
    @Column(nullable = false, length = 64)
    @NotNull
    @Size(max = 64)
    private String name;
    
    @Column(nullable = false, unique = true, length = 32)
    @NotNull
    @Size(min = 4, max = 32)
    private String username;
    
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
    
    //Entity relationship
    @OneToMany(mappedBy = "partner")
    private List<CustomerEntity> customers;
    
    @OneToMany(mappedBy = "partner")
    private List<RentalRecordEntity> rentalRecords;

    
    public PartnerEntity() {
        customers = new ArrayList<>();
        rentalRecords = new ArrayList<>();
    }

    public PartnerEntity(String name, String username, String password) {
        this();
        
        this.name = name;
        this.username = username;
        this.password = password;
    }
    
    
    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<CustomerEntity> customers) {
        this.customers = customers;
    }

    public List<RentalRecordEntity> getRentalRecords() {
        return rentalRecords;
    }

    public void setRentalRecords(ArrayList<RentalRecordEntity> rentalRecords) {
        this.rentalRecords = rentalRecords;
    }
    
}
