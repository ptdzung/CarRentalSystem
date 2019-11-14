/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrsmanagementclient;

import ejb.session.stateless.CarSessionBeanRemote;
import ejb.session.stateless.RentalRateSessionBeanRemote;
import entity.EmployeeEntity;
import entity.RentalRateEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.enumerator.AccessRightEnum;
import util.exception.CarCategoryNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.InvalidAccessRightException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
public class SalesModule {
    private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    private RentalRateSessionBeanRemote rentalRateSessionBeanRemote;
    private CarSessionBeanRemote carSessionBeanRemote;
    
    private EmployeeEntity currentEmployee;

    public SalesModule() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public SalesModule(RentalRateSessionBeanRemote rentalRateSessionBeanRemote, CarSessionBeanRemote carSessionBeanRemote, EmployeeEntity currentEmployee) {
        this();
        
        this.rentalRateSessionBeanRemote = rentalRateSessionBeanRemote;
        this.carSessionBeanRemote = carSessionBeanRemote;
        this.currentEmployee = currentEmployee;
    }
    
    public void menuSales() throws InvalidAccessRightException {
        if(currentEmployee.getAccessRightEnum() != AccessRightEnum.SALES)
        {
            throw new InvalidAccessRightException("You don't have SALES rights to access this module.");
        }
        
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        while(true) {
            System.out.println("*** Management System :: Sales ***\n");
            System.out.println("1: Create New Rental Rate");
            System.out.println("2: View Rental Rate Details");
            System.out.println("3: View All Rental Rates");
            System.out.println("4: Back\n");
            
            while(response < 1 || response > 4) {
                System.out.print("> ");
                
                response = sc.nextInt();
                
                if(response == 1) {
                    doCreateNewRentalRate();
                } else if (response == 2) {
                    doViewRentalRateDetails();
                } else if (response == 3) {
                    doViewAllRentalRates();
                } else if (response == 4) {
                    break;
                } else {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
            
            if(response == 4) break;
        }
    }

    private void doCreateNewRentalRate() {
        Scanner sc = new Scanner(System.in);
        RentalRateEntity rentalRate = new RentalRateEntity();
        
        System.out.println("*** Management System :: Sales :: Create New Rental Rate ***\n");
        System.out.print("Enter rate name> ");
        rentalRate.setRateName(sc.nextLine().trim());
        System.out.print("Enter rate per day> ");
        rentalRate.setRatePerDay(new BigDecimal(sc.nextLine().trim()));
        System.out.print("Enter car category> ");
        try {
            rentalRate.setCarCategory(carSessionBeanRemote.retrieveCarCategoryByName(sc.nextLine().trim()));
        } catch(CarCategoryNotFoundException ex) {
            ex.getMessage();
        }
        
        System.out.print("Enter rate start time, blank if indefinite time (Input format: year, month, day, hour, minute)> ");
        int startYear = sc.nextInt();
        if (Integer.toString(startYear).length() > 0) {
            rentalRate.setStartDate(new Date(startYear-1900, sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        
        System.out.print("Enter rate end time, blank if indefinite time (Input format: year, month, day, hour, minute)> ");
        int endYear = sc.nextInt();
        if (Integer.toString(endYear).length() > 0) {
            rentalRate.setStartDate(new Date(endYear-1900, sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        
        Set<ConstraintViolation<RentalRateEntity>>constraintViolations = validator.validate(rentalRate);
        
        if(constraintViolations.isEmpty()) {
              try {
                Long rentalRateId = rentalRateSessionBeanRemote.createNewRentalRate(rentalRate);
                System.out.println("New rental rate created successfully!: " + rentalRateId + "\n");
            } catch(UnknownPersistenceException ex) {
                System.out.println("An unknown error has occurred while creating the new staff!: " + ex.getMessage() + "\n");
            } catch(InputDataValidationException ex) {
                System.out.println(ex.getMessage() + "\n");
            }
        } else {
            showInputDataValidationErrorsForRentalRate(constraintViolations);
        }
            
    }

    private void doViewRentalRateDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void doViewAllRentalRates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void showInputDataValidationErrorsForRentalRate(Set<ConstraintViolation<RentalRateEntity>>constraintViolations)
    {
        System.out.println("\nInput data validation error!:");
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            System.out.println("\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage());
        }

        System.out.println("\nPlease try again......\n");
    }
}
