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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Pham The Dzung
 */
@Entity
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    
    @Column(nullable = false, length = 64)
    @NotNull
    @Size(max = 64)
    private String name;
    
    @Column(nullable = false, unique = true, length = 128)
    @NotNull
    @Size(max = 128)
    @Email
    private String email;
    
    //Entity relationship
    @ManyToOne
    private PartnerEntity partner;
    
    @OneToMany(mappedBy = "customer")
    private List<RentalRecordEntity> rentalRecords;

    public CustomerEntity() {
        rentalRecords = new ArrayList<>();
    }

    public CustomerEntity(String name, String email, PartnerEntity partner) {
        this();
        
        this.name = name;
        this.email = email;
        this.partner = partner;
    }
    
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PartnerEntity getPartner() {
        return partner;
    }

    public void setPartner(PartnerEntity partner) {
        this.partner = partner;
    }

    public List<RentalRecordEntity> getRentalRecords() {
        return rentalRecords;
    }

    public void setRentalRecords(ArrayList<RentalRecordEntity> rentalRecords) {
        this.rentalRecords = rentalRecords;
    }

}
