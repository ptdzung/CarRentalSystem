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
import java.util.List;
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
import util.exception.RentalRateNotFoundException;
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
            response = 0;
            
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
        rentalRate.setRatePerDay(sc.nextBigDecimal());
        sc.nextLine();
        System.out.print("Enter car category> ");
        try {
            rentalRate.setCarCategory(carSessionBeanRemote.retrieveCarCategoryByName(sc.nextLine().trim()));
        } catch(CarCategoryNotFoundException ex) {
            ex.getMessage();
        }
        
        System.out.print("Enter rate start time, 0 if indefinite time (Input format: year month day hour minute)> ");
        int input = sc.nextInt();
        if (input > 0) {
            rentalRate.setStartDate(new Date(input-1900, sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        
        System.out.print("Enter rate end time, blank if indefinite time (Input format: year month day hour minute)> ");
        input = sc.nextInt();
        if (input > 0) {
            rentalRate.setEndDate(new Date(input-1900, sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        
        Set<ConstraintViolation<RentalRateEntity>>constraintViolations = validator.validate(rentalRate);
        
        if(constraintViolations.isEmpty()) {
            try {
                Long rentalRateId = rentalRateSessionBeanRemote.createNewRentalRate(rentalRate);
                System.out.println("New rental rate created successfully!: " + rentalRateId + "\n");
            } catch(UnknownPersistenceException ex) {
                System.out.println("An unknown error has occurred while creating the new rental rate: " + ex.getMessage() + "\n");
            } catch(InputDataValidationException ex) {
                System.out.println(ex.getMessage() + "\n");
            }
        } else {
            showInputDataValidationErrorsForRentalRate(constraintViolations);
        }
            
    }

    private void doViewRentalRateDetails() {
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        System.out.println("*** Management System :: Sales :: View Rental Rate Details ***\n");
        System.out.print("Enter Rate Name> ");
        String rateName = sc.nextLine().trim();
        System.out.print("Enter Category Name> ");
        String categoryName = sc.nextLine().trim();
        
        try
        {
            RentalRateEntity rentalRate = rentalRateSessionBeanRemote.retrieveRentalRate(rateName, categoryName);
            System.out.printf("%20s%20s%20s\n", "Rate Name", "Rate per Day", "Car Category");
            System.out.printf("%20s%20s%20s\n", rentalRate.getRateName(), rentalRate.getRatePerDay().toString(), rentalRate.getCarCategory().getCategoryName());
            System.out.println("------------------------");
            System.out.println("1: Update Rental Rate");
            System.out.println("2: Delete Rental Rate");
            System.out.println("3: Back\n");
            System.out.print("> ");
            response = sc.nextInt();

            if(response == 1)
            {
                doUpdateRate(rentalRate);
            }
            else if(response == 2)
            {
                doDeleteRate(rentalRate);
            }
        }
        catch(RentalRateNotFoundException | CarCategoryNotFoundException ex)
        {
            System.out.println("An error has occurred while retrieving rental rate: " + ex.getMessage() + "\n");
        }
    }

    private void doViewAllRentalRates() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("*** Management System :: Sales :: View All Rental Ratels ***\n");
        
        List<RentalRateEntity> rates = rentalRateSessionBeanRemote.retrieveAllRentalRates();
        System.out.printf("%15s%20s%20s%20s\n", "Rental Rate ID", "Rate Name", "Rate per Day", "Car Category");

        for(RentalRateEntity r:rates)
        {
            System.out.printf("%15s%20s%20s%20s\n", r.getRentalRateId().toString(), r.getRateName(), r.getRatePerDay().toString(), r.getCarCategory().getCategoryName());
        }
        
        System.out.print("Press any key to continue...> ");
        sc.nextLine();
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

    private void doUpdateRate(RentalRateEntity rentalRate) {
        Scanner sc = new Scanner(System.in);
        String input;
        BigDecimal inputDec;
        
        System.out.println("*** Management System :: Sales :: Update Rental Rate ***\n");
        System.out.print("Enter rate name (blank if no change)> ");
        input = sc.nextLine().trim();
        if (input.length() > 0) {
            rentalRate.setRateName(input);
        }
        System.out.print("Enter rate per day (blank if no change)> ");
        inputDec = sc.nextBigDecimal();
        if (inputDec.compareTo(BigDecimal.ZERO) > 0) {
            rentalRate.setRatePerDay(inputDec);
        }
        sc.nextLine();
        System.out.print("Enter car category (blank if no change)> ");
        input = sc.nextLine().trim();
        if (input.length() > 0) {
            try {
                rentalRate.setCarCategory(carSessionBeanRemote.retrieveCarCategoryByName(input));
            } catch(CarCategoryNotFoundException ex) {
                ex.getMessage();
            }
        }
        
        System.out.print("Enter rate start time, 0 if no change (Input format: year month day hour minute)> ");
        int i = sc.nextInt();
        if (i > 0) {
            rentalRate.setStartDate(new Date(i-1900, sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        
        System.out.print("Enter rate end time, 0 if no change (Input format: year month day hour minute)> ");
        i = sc.nextInt();
        if (i > 0) {
            rentalRate.setEndDate(new Date(i-1900, sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        
        Set<ConstraintViolation<RentalRateEntity>>constraintViolations = validator.validate(rentalRate);
        
        if(constraintViolations.isEmpty()) {
            try {
                rentalRateSessionBeanRemote.updateRentalRate(rentalRate);
                System.out.println("Rental rate updated successfully!\n");
            } catch(RentalRateNotFoundException ex) {
                System.out.println("An error has occurred while updating rental rate: " + ex.getMessage() + "\n");
            } catch(InputDataValidationException ex) {
                System.out.println(ex.getMessage() + "\n");
            }
        } else {
            showInputDataValidationErrorsForRentalRate(constraintViolations);
        }
           
    }

    private void doDeleteRate(RentalRateEntity rentalRate) {
        Scanner sc = new Scanner(System.in);     
        String input;
        
        System.out.println("*** Management System :: Sales :: Delete Rental Rate ***\n");
        System.out.printf("Confirm Delete Rental Rate %s (Enter 'Y' to Delete)> ", rentalRate.getRateName());
        input = sc.nextLine().trim();
        
        if(input.equals("Y"))
        {
            try 
            {
                if (rentalRateSessionBeanRemote.deleteRentalRate(rentalRate.getRentalRateId())) {
                    System.out.println("Rental rate deleted successfully!\n");
                } else {
                    System.out.println("Rental Rate ID " + rentalRate.getRentalRateId() + " cannot be deleted completely!");
                }
            } 
            catch (RentalRateNotFoundException ex) 
            {
                System.out.println("An error has occurred while deleting rental rate: " + ex.getMessage() + "\n");
            }
        }
        else
        {
            System.out.println("Rental Rate NOT deleted!\n");
        }
    }
}
