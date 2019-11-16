/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrsmanagementclient;

import ejb.session.stateless.CarSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.ReservationRecordSessionBeanRemote;
import entity.CarEntity;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import entity.RentalRecordEntity;
import java.util.Scanner;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.enumerator.AccessRightEnum;
import util.enumerator.StatusEnum;
import util.exception.InvalidAccessRightException;
import util.exception.RentalRecordNotFoundException;

/**
 *
 * @author Pham The Dzung
 */
public class ServiceModule {
    private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    private CarSessionBeanRemote carSessionBeanRemote;
    private ReservationRecordSessionBeanRemote reservationRecordSessionBeanRemote;
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    
    private EmployeeEntity currentEmployee;

    public ServiceModule() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public ServiceModule(CarSessionBeanRemote carSessionBeanRemote, ReservationRecordSessionBeanRemote reservationRecordSessionBeanRemote, CustomerSessionBeanRemote customerSessionBeanRemote, EmployeeEntity currentEmployee) {
        this();
        
        this.carSessionBeanRemote = carSessionBeanRemote;
        this.reservationRecordSessionBeanRemote = reservationRecordSessionBeanRemote;
        this.customerSessionBeanRemote = customerSessionBeanRemote;
        this.currentEmployee = currentEmployee;
    }
    
    public void menuService() throws InvalidAccessRightException {
        if(currentEmployee.getAccessRightEnum() != AccessRightEnum.SERVICE)
        {
            throw new InvalidAccessRightException("You don't have SERVICE rights to access this module.");
        }
        
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        while(true) {
            System.out.println("*** Management System :: Service ***\n");
            System.out.println("1: Pickup Car");
            System.out.println("2: Return Car");
            System.out.println("3: Back\n");
            response = 0;
            
            while(response < 1 || response > 3) {
                System.out.print("> ");
                
                response = sc.nextInt();
                
                if(response == 1) {
                    doPickup();
                } else if (response == 2) {
                    doReturn();
                } else if (response == 3) {
                    break;
                } else {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
            
            if(response == 3) break;
        }
    }

    private void doPickup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*** Management System :: Service :: Pickup Car ***\n");
        System.out.print("Enter Reservation ID> ");
        try {
            RentalRecordEntity record = reservationRecordSessionBeanRemote.retrieveReservationById(sc.nextLong());
            if (!record.isHasPaid()) {
                record.setHasPaid(true);
            }
            
            CarEntity car = record.getCar();
            car.setOutlet(null);
            car.setStatus(StatusEnum.RENTED);  
            System.out.println("Reservation ID " + record.getRentalRecordId() + " update: Car is picked up!");
        } catch (RentalRecordNotFoundException ex) {
            ex.getMessage();
        }
    }

    private void doReturn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*** Management System :: Service :: Return Car ***\n");
        System.out.print("Enter Reservation ID> ");
        try {
            RentalRecordEntity record = reservationRecordSessionBeanRemote.retrieveReservationById(sc.nextLong());
            CarEntity car = record.getCar();
            car.setOutlet(record.getReturnOutlet());
            car.setStatus(StatusEnum.AVAILABLE);  
            car.setRentalRecord(null);
            System.out.println("Reservation ID " + record.getRentalRecordId() + " update: Car is returned!");
        } catch (RentalRecordNotFoundException ex) {
            ex.getMessage();
        }
    }
    
}
