/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.CarSessionBeanLocal;
import ejb.session.stateless.EmployeeSessionBeanLocal;
import ejb.session.stateless.RentalRateSessionBeanLocal;
import entity.CarCategoryEntity;
import entity.CarModelEntity;
import entity.EmployeeEntity;
import entity.OutletEntity;
import entity.PartnerEntity;
import entity.RentalRateEntity;
import java.math.BigDecimal;
import java.sql.Time;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import javax.ejb.EJB;
import util.enumerator.AccessRightEnum;
import util.enumerator.StatusEnum;
import util.exception.InputDataValidationException;
import util.exception.LicensePlateExistException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
@Singleton
@LocalBean
@Startup
public class DataInitialisationSessionBean {

    @EJB
    private EmployeeSessionBeanLocal employeeSessionBeanLocal;

    @EJB
    private RentalRateSessionBeanLocal rentalRateSessionBeanLocal;

    @EJB
    private CarSessionBeanLocal carSessionBeanLocal;
    
    
    @PersistenceContext(unitName = "CarRentalManagementSystem-ejbPU")
    private EntityManager em;

    public DataInitialisationSessionBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        if(em.find(OutletEntity.class, 1l) == null) {
            initialiseData();
        }
    }
    
    private void initialiseData() {
        OutletEntity outlet1 = new OutletEntity("Outlet A");
        em.persist(outlet1);
        em.flush();
        OutletEntity outlet2 = new OutletEntity("Outlet B");
        em.persist(outlet2);
        em.flush();
        OutletEntity outlet3 = new OutletEntity("Outlet C", new Time(10,0,0), new Time(22,0,0));
        em.persist(outlet3);
        em.flush();
        
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee A1", AccessRightEnum.SALES, "a1sales", "password", outlet1));
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee A2", AccessRightEnum.OPERATIONS, "a2operations", "password", outlet1));
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee A3", AccessRightEnum.SERVICE, "a3service", "password", outlet1));
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee A4", AccessRightEnum.STANDARD, "a4standard", "password", outlet1));
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee A5", AccessRightEnum.STANDARD, "a5standard", "password", outlet1));
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee B1", AccessRightEnum.SALES, "b1sales", "password", outlet2));
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee B2", AccessRightEnum.OPERATIONS, "b2operations", "password", outlet2));
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee B3", AccessRightEnum.SERVICE, "b3service", "password", outlet2));
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee C1", AccessRightEnum.SALES, "c1sales", "password", outlet3));
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee C2", AccessRightEnum.OPERATIONS, "c2operations", "password", outlet3));
        employeeSessionBeanLocal.createNewEmployee(new EmployeeEntity("Employee C3", AccessRightEnum.SERVICE, "c3service", "password", outlet3));
        
        CarCategoryEntity category1 = new CarCategoryEntity("Standard Sedan");
        em.persist(category1);
        em.flush();
        CarCategoryEntity category2 = new CarCategoryEntity("Family Sedan");
        em.persist(category2);
        em.flush();
        CarCategoryEntity category3 = new CarCategoryEntity("Luxury Sedan");
        em.persist(category3);
        em.flush();
        CarCategoryEntity category4 = new CarCategoryEntity("SUV and Minivan");
        em.persist(category4);
        em.flush();
        
        try {
            carSessionBeanLocal.createNewCarModel(new CarModelEntity("Corolla", "Toyota", category1));
            carSessionBeanLocal.createNewCarModel(new CarModelEntity("Civic", "Honda", category1));
            carSessionBeanLocal.createNewCarModel(new CarModelEntity("Sunny", "Nissan", category1));
            carSessionBeanLocal.createNewCarModel(new CarModelEntity("E Class", "Mercedes", category3));
            carSessionBeanLocal.createNewCarModel(new CarModelEntity("5 Series", "BMW", category3));
            carSessionBeanLocal.createNewCarModel(new CarModelEntity("A6", "Audi", category3));

            carSessionBeanLocal.createNewCar("SS00A1TC", "Toyota", "Corolla", StatusEnum.AVAILABLE, outlet1);
            carSessionBeanLocal.createNewCar("SS00A2TC", "Toyota", "Corolla", StatusEnum.AVAILABLE, outlet1);
            carSessionBeanLocal.createNewCar("SS00A3TC", "Toyota", "Corolla", StatusEnum.AVAILABLE, outlet1);
            carSessionBeanLocal.createNewCar("SS00B1HC", "Honda", "Civic", StatusEnum.AVAILABLE, outlet2);
            carSessionBeanLocal.createNewCar("SS00B2HC", "Honda", "Civic", StatusEnum.AVAILABLE, outlet2);
            carSessionBeanLocal.createNewCar("SS00B3HC", "Honda", "Civic", StatusEnum.AVAILABLE, outlet2);
            carSessionBeanLocal.createNewCar("SS00C1NS", "Nissan", "Sunny", StatusEnum.AVAILABLE, outlet3);
            carSessionBeanLocal.createNewCar("SS00C2NS", "Nissan", "Sunny", StatusEnum.AVAILABLE, outlet3);
            carSessionBeanLocal.createNewCar("SS00C3NS", "Nissan", "Sunny", StatusEnum.REPAIR, outlet3);
            carSessionBeanLocal.createNewCar("LS00A4ME", "Mercedes", "E Class", StatusEnum.AVAILABLE, outlet1);
            carSessionBeanLocal.createNewCar("LS00B4B5", "BMW", "5 Series", StatusEnum.AVAILABLE, outlet2);
            carSessionBeanLocal.createNewCar("LS00C4A6", "Audi", "A6", StatusEnum.AVAILABLE, outlet3);
        
            rentalRateSessionBeanLocal.createNewRentalRate(new RentalRateEntity("Default", new BigDecimal("100"), null, null, category1));
            rentalRateSessionBeanLocal.createNewRentalRate(new RentalRateEntity("Weekend Promo", new BigDecimal("80"), 
                                                           new Date(2019-1900, 11, 6, 12, 0), new Date(2019-1900, 11, 8, 0, 0), category1));
            rentalRateSessionBeanLocal.createNewRentalRate(new RentalRateEntity("Default", new BigDecimal("200"), null, null, category2));
            rentalRateSessionBeanLocal.createNewRentalRate(new RentalRateEntity("Monday", new BigDecimal("310"), 
                                                           new Date(2019-1900, 11, 2, 0, 0), new Date(2019-1900, 11, 2, 23, 59), category3));
            rentalRateSessionBeanLocal.createNewRentalRate(new RentalRateEntity("Tuesday", new BigDecimal("320"), 
                                                           new Date(2019-1900, 11, 3, 0, 0), new Date(2019-1900, 11, 3, 23, 59), category3));
            rentalRateSessionBeanLocal.createNewRentalRate(new RentalRateEntity("Wednesday", new BigDecimal("330"), 
                                                           new Date(2019-1900, 11, 4, 0, 0), new Date(2019-1900, 11, 4, 23, 59), category3));
            rentalRateSessionBeanLocal.createNewRentalRate(new RentalRateEntity("Weekday Promo", new BigDecimal("250"), 
                                                           new Date(2019-1900, 11, 4, 12, 0), new Date(2019-1900, 11, 5, 12, 0), category3));
        } catch (InputDataValidationException | UnknownPersistenceException | LicensePlateExistException ex) {
            ex.printStackTrace();
        }
    
        PartnerEntity partner = new PartnerEntity("Holiday.com", "holidaycom", "password");
        em.persist(partner);
        em.flush();
        
    }
}
