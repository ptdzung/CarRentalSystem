/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrsmanagementclient;

import ejb.session.stateless.CarSessionBeanRemote;
import ejb.session.stateless.DispatchSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import entity.CarEntity;
import entity.CarModelEntity;
import entity.EmployeeEntity;
import entity.OutletEntity;
import entity.TravelDispatchRecordEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.enumerator.AccessRightEnum;
import util.enumerator.StatusEnum;
import util.exception.CarCategoryNotFoundException;
import util.exception.CarModelNotFoundException;
import util.exception.CarNotFoundException;
import util.exception.EmployeeNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.InvalidAccessRightException;
import util.exception.LicensePlateExistException;
import util.exception.TravelDispatchRecordNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
public class OperationsModule {
    private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    private CarSessionBeanRemote carSessionBeanRemote;
    private EmployeeSessionBeanRemote employeeSessionBeanRemote;
    private DispatchSessionBeanRemote dispatchSessionBeanRemote;
    
    private EmployeeEntity currentEmployee;
    
    
    
    public OperationsModule() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public OperationsModule(CarSessionBeanRemote carSessionBeanRemote, EmployeeSessionBeanRemote employeeSessionBeanRemote, DispatchSessionBeanRemote dispatchSessionBeanRemote, EmployeeEntity currentEmployee) {
        this();
        
        this.carSessionBeanRemote = carSessionBeanRemote;
        this.employeeSessionBeanRemote = employeeSessionBeanRemote;
        this.currentEmployee = currentEmployee;
        this.dispatchSessionBeanRemote = dispatchSessionBeanRemote;
    }
    
    public void menuOperations() throws InvalidAccessRightException {
        if(currentEmployee.getAccessRightEnum() != AccessRightEnum.OPERATIONS)
        {
            throw new InvalidAccessRightException("You don't have OPERATIONS rights to access this module.");
        }
        
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        while(true) {
            System.out.println("*** Management System :: Operations ***\n");
            System.out.println("1: Create New Car Model");
            System.out.println("2: View All Car Models");
            System.out.println("3: Update Car Model");
            System.out.println("4: Delete Car Model");
            System.out.println("-----------------------");
            System.out.println("5: Create New Car");
            System.out.println("6: View Car Details");
            System.out.println("7: View All Cars");
            System.out.println("-----------------------");
            System.out.println("8: View Transit Driver Dispatch Records for Current Day Reservations");
            System.out.println("9: Assign Transit Driver");
            System.out.println("10: Update Transit As Completed");
            System.out.println("-----------------------");
            System.out.println("11: Back\n");
            response = 0;
            
            while(response < 1 || response > 11) {
                System.out.print("> ");
                
                response = sc.nextInt();
                
                if(response == 1) {
                    doCreateNewCarModel();
                } else if (response == 2) {
                    doViewAllCarModels();
                } else if (response == 3) {
                    doUpdateCarModel();
                } else if (response == 4) {
                    doDeleteCarModel();
                } else if (response == 5) {
                    doCreateNewCar();
                } else if (response == 6) {
                    doViewCarDetails();
                } else if (response == 7) {
                    doViewAllCars();
                } else if (response == 8) {
                    doViewTransitToday();
                } else if (response == 9) {
                    doAssignDriver();
                } else if (response == 10) {
                    doUpdateTransit();
                } else if (response == 11) {
                    break;
                } else {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
            
            if(response == 11) break;
        }
    }

    private void doCreateNewCarModel() {
        Scanner sc = new Scanner(System.in);
        CarModelEntity cm = new CarModelEntity();
        
        System.out.println("*** Management System :: Operations :: Create New Car Model ***\n");
        System.out.print("Enter Car Make> ");
        cm.setMake(sc.nextLine().trim());
        System.out.print("Enter Car Model> ");
        cm.setModelName(sc.nextLine().trim());
        System.out.print("Enter Car Category> ");
        try {
            cm.setCarCategory(carSessionBeanRemote.retrieveCarCategoryByName(sc.nextLine().trim()));
        } catch(CarCategoryNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        Set<ConstraintViolation<CarModelEntity>>constraintViolations = validator.validate(cm);
        
        if(constraintViolations.isEmpty()) {
            try {
                Long carModelId = carSessionBeanRemote.createNewCarModel(cm);
                System.out.println("New car model created successfully!: " + carModelId + "\n");
            } catch(UnknownPersistenceException ex) {
                System.out.println("An unknown error has occurred while creating the new model: " + ex.getMessage() + "\n");
            } catch(InputDataValidationException ex) {
                System.out.println(ex.getMessage() + "\n");
            }
        } else {
            showInputDataValidationErrorsForCarModel(constraintViolations);
        }
    }

    private void doViewAllCarModels() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("*** Management System :: Operations :: View All Car Models ***\n");
        
        List<CarModelEntity> cm = carSessionBeanRemote.retrieveAllCarModels();
        System.out.printf("%15s%20s%20s%20s\n", "Car Model ID", "Car Make", "Car Model Name", "Car Category");

        for(CarModelEntity c : cm)
        {
            System.out.printf("%15s%20s%20s%20s\n", c.getCarModelId().toString(), c.getMake(), c.getModelName(), c.getCarCategory().getCategoryName());
        }
        
        System.out.print("Press any key to continue...> ");
        sc.nextLine();
    }

    private void doUpdateCarModel() {
        try {
            Scanner sc = new Scanner(System.in);
            String input;
            CarModelEntity cm;

            System.out.println("*** Management System :: Operations :: Update Car Model ***\n");
            System.out.print("Enter Car Model ID> ");
            input = sc.nextLine().trim();
            cm = carSessionBeanRemote.retrieveCarModelById(Long.parseLong(input));

            System.out.print("Enter Car Make (blank if no change)> ");
            input = sc.nextLine().trim();
            if (input.length() > 0) {
                cm.setMake(input);
            }
            System.out.print("Enter Car Model (blank if no change)> ");
            input = sc.nextLine().trim();
            if (input.length() > 0) {
                cm.setModelName(input);
            }
            System.out.print("Enter Car Category (blank if no change)> ");
            input = sc.nextLine().trim();
            if (input.length() > 0) {
                try {
                    cm.setCarCategory(carSessionBeanRemote.retrieveCarCategoryByName(input));
                } catch(CarCategoryNotFoundException ex) {
                    System.out.println(ex.getMessage() + "\n");
                }
            }

            Set<ConstraintViolation<CarModelEntity>>constraintViolations = validator.validate(cm);

            if(constraintViolations.isEmpty()) {
                try {
                    carSessionBeanRemote.updateCarModel(cm);
                    System.out.println("Car model updated successfully!\\n");
                } catch(CarModelNotFoundException ex) {
                    System.out.println("An error has occurred while updating car model: " + ex.getMessage() + "\n");
                } catch(InputDataValidationException ex) {
                    System.out.println(ex.getMessage() + "\n");
                }
            } else {
                showInputDataValidationErrorsForCarModel(constraintViolations);
            }
        } catch (CarModelNotFoundException ex) {
            ex.getMessage();
        }
    }

    private void doDeleteCarModel() {
        Scanner sc = new Scanner(System.in);     
        
        System.out.println("*** Management System :: Operations :: Delete Car Model ***\n");
        System.out.println("Enter Car Model ID> ");
        Long carModelId = sc.nextLong();
        System.out.printf("Confirm Delete Car Model %s (Enter 'Y' to Delete)> ", carModelId.toString());
        
        try {
            if (carSessionBeanRemote.deleteCarModel(carModelId)) {
                System.out.println("Car model deleted successfully!\n");
            } else {
                System.out.println("Car Model ID " + carModelId + " cannot be deleted completely!");
            }
        } catch (CarModelNotFoundException ex) {
            System.out.println("An error has occurred while deleting car model: " + ex.getMessage() + "\n");
        }
        
    }

    private void doCreateNewCar() {
        Scanner sc = new Scanner(System.in);
        CarEntity car = new CarEntity();
        
        System.out.println("*** Management System :: Operations :: Create New Car ***\n");
        System.out.print("Enter License Plate> ");
        car.setLicensePlate(sc.nextLine().trim());
        System.out.print("Enter Car Make> ");
        String make = sc.nextLine().trim();
        System.out.print("Enter Car Model Name> ");
        String modelName = sc.nextLine().trim();
        try {
            car.setCarModel(carSessionBeanRemote.retrieveCarModel(make, modelName));
        } catch (CarModelNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        while(true)
        {
            System.out.print("Select Car Status (1: Available, 2: Repair, 3: Disabled)> ");
            Integer i = sc.nextInt();
            
            if(i >= 1 && i <= 3)
            {
                car.setStatus(StatusEnum.values()[i-1]);
                break;
            }
            else
            {
                System.out.println("Invalid option, please try again!\n");
            }
        }
        
        while(true)
        {
            System.out.print("Select Car Outlet (1: A, 2: B, 3: C)> ");
            Long i = sc.nextLong();
            
            if(i >= 1 && i <= 3)
            {
                car.setOutlet(carSessionBeanRemote.retrieveOutletById(i));
                break;
            }
            else
            {
                System.out.println("Invalid option, please try again!\n");
            }
        }
        
        Set<ConstraintViolation<CarEntity>>constraintViolations = validator.validate(car);
        
        if(constraintViolations.isEmpty()) {
            try {
                Long carId = carSessionBeanRemote.createNewCar(car);
                System.out.println("New car created successfully!: " + carId + "\n");
            } catch(UnknownPersistenceException ex) {
                System.out.println("An unknown error has occurred while creating the new car: " + ex.getMessage() + "\n");
            } catch(InputDataValidationException | LicensePlateExistException ex) {
                System.out.println(ex.getMessage() + "\n");
            }
        } else {
            showInputDataValidationErrorsForCar(constraintViolations);
        }
    }

    private void doViewCarDetails() {
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        System.out.println("*** Management System :: Operations :: View Car Details ***\n");
        System.out.print("Enter Car License Plate> ");
        String licensePlate = sc.nextLine().trim();
        
        try
        {
            CarEntity car = carSessionBeanRemote.retrieveCarByLicensePlate(licensePlate);
            System.out.printf("%15s%20s%20s%15s%15s\n", "License Plate", "Make", "Model", "Status", "Outlet");
            System.out.printf("%15s%20s%20s%15s%15s\n", car.getLicensePlate(), car.getCarModel().getMake(), car.getCarModel().getModelName(), car.getStatus().toString(), "" + car.getOutlet().getName());
            System.out.println("------------------------");
            System.out.println("1: Update Car");
            System.out.println("2: Delete Car");
            System.out.println("3: Back\n");
            System.out.print("> ");
            response = sc.nextInt();

            if(response == 1)
            {
                doUpdateCar(car);
            }
            else if(response == 2)
            {
                doDeleteCar(car);
            }
        }
        catch(CarNotFoundException | InputDataValidationException ex)
        {
            System.out.println("An error has occurred while retrieving car: " + ex.getMessage() + "\n");
        }
    }

    private void doViewAllCars() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("*** Management System :: Operations :: View All Cars ***\n");
        
        List<CarEntity> cars = carSessionBeanRemote.retrieveAllCars();
        System.out.printf("%15s%20s%20s%15s%15s\n", "License Plate", "Make", "Model", "Status", "Outlet");

        for(CarEntity car : cars)
        {
            System.out.printf("%15s%20s%20s%15s%15s\n", car.getLicensePlate(), car.getCarModel().getMake(), car.getCarModel().getModelName(), car.getStatus().toString(), "" + car.getOutlet().getName());
        }
        
        System.out.print("Press any key to continue...> ");
        sc.nextLine();
    }

    private void doViewTransitToday() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("*** Management System :: Operations :: View Transit Driver Dispatch Records for Current Day Reservations ***\n");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        OutletEntity outlet = currentEmployee.getOutlet();
        
        List<TravelDispatchRecordEntity> list = dispatchSessionBeanRemote.retrieveTravelDispatchRecordByDate(date, outlet);
        
        
        if(list.isEmpty()) System.out.println("No transit to be done");
        else {
            System.out.printf("%20s%16s%20s%12s\n", "Dispatch Record ID", "Rental Record ID", "Driver", "Has Arrived");
            for(TravelDispatchRecordEntity r : list) {
                System.out.printf("%20s%16s%20s%12s\n", r.getTravelDispatchRecordId().toString(), r.getRentalRecord().getRentalRecordId().toString(), "" + r.getDriver().getName(), r.getStatus().toString());
            }
        }
        
        System.out.print("Press any key to continue...> ");
        sc.nextLine();
    }

    private void doAssignDriver() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("*** Management System :: Operations :: Assign Transit Driver ***\n");
        System.out.print("Enter Travel Dispatch Record ID> ");
        Long input = sc.nextLong();
        try {
            TravelDispatchRecordEntity record = dispatchSessionBeanRemote.retrieveTravelDispatchRecordById(input);
            System.out.print("Enter Employee Username> ");
            String username = sc.nextLine().trim();
            EmployeeEntity emp = employeeSessionBeanRemote.retrieveEmployeeByUsername(username);
            if (record.getDriver() == null) {
                record.setDriver(emp);
                System.out.println("Successfully assigned employee " + emp.getName() + " to Dispatch Record ID " + input);
            } else {
                System.out.println("A driver is already assigned to Dispatch Record ID " + input);
            }
        } catch (TravelDispatchRecordNotFoundException | EmployeeNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
    }

    private void doUpdateTransit() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("*** Management System :: Operations :: Update Transit Status ***\n");
        System.out.print("Enter Travel Dispatch Record ID> ");
        Long input = sc.nextLong();
        try {
            TravelDispatchRecordEntity record = dispatchSessionBeanRemote.retrieveTravelDispatchRecordById(input);
            record.setStatus(Boolean.TRUE);
            record.getRentalRecord().getCar().setOutlet(record.getReceivingOutlet());
        } catch (TravelDispatchRecordNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }
    
    private void showInputDataValidationErrorsForCarModel(Set<ConstraintViolation<CarModelEntity>>constraintViolations)
    {
        System.out.println("\nInput data validation error!:");
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            System.out.println("\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage());
        }

        System.out.println("\nPlease try again......\n");
    }
    
    private void showInputDataValidationErrorsForCar(Set<ConstraintViolation<CarEntity>>constraintViolations)
    {
        System.out.println("\nInput data validation error!:");
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            System.out.println("\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage());
        }

        System.out.println("\nPlease try again......\n");
    }

    private void doUpdateCar(CarEntity car) {
        Scanner sc = new Scanner(System.in);
        String input;
        
        System.out.println("*** Management System :: Operations :: Update Car ***\n");
        System.out.print("Enter Car License Plate (blank if no change)> ");
        input = sc.nextLine().trim();
        if (input.length() > 0) {
            car.setLicensePlate(input);
        }
        
        System.out.print("Select Car Status (1: Available, 2: Repair, 3: Disabled) (blank if no change)> ");
        input = sc.nextLine().trim();
        if (input.length() > 0) {
            int i = Integer.parseInt(input);
            car.setStatus(StatusEnum.values()[i-1]);
        }
        
        System.out.print("Select Car Outlet (0: No outlet, 1: A, 2: B, 3: C) (blank if no change)> ");
        input = sc.nextLine().trim();
        if (input.length() > 0) {
            Long outletId = Long.parseLong(input);
            if(outletId >= 1 && outletId <= 3) {
                car.setOutlet(carSessionBeanRemote.retrieveOutletById(outletId));
            } else if (outletId == 0) {
                car.setOutlet(null);
            }
        }
           
        Set<ConstraintViolation<CarEntity>>constraintViolations = validator.validate(car);
        
        if(constraintViolations.isEmpty()) {
            try {
                carSessionBeanRemote.updateCar(car);
                System.out.println("Car updated successfully!\n");
            } catch(CarNotFoundException ex) {
                System.out.println("An error has occurred while updating car: " + ex.getMessage() + "\n");
            } catch(InputDataValidationException ex) {
                System.out.println(ex.getMessage() + "\n");
            }
        } else {
            showInputDataValidationErrorsForCar(constraintViolations);
        }
    }

    private void doDeleteCar(CarEntity car) {
        Scanner sc = new Scanner(System.in);     
        String input;
        
        System.out.println("*** Management System :: Operations :: Delete Car ***\n");
        System.out.printf("Confirm Delete Car %s (Enter 'Y' to Delete)> ", car.getLicensePlate());
        input = sc.nextLine().trim();
        
        if(input.equals("Y"))
        {
            try 
            {
                if (carSessionBeanRemote.deleteCar(car.getCarId())) {
                    System.out.println("Car deleted successfully!\n");
                } else {
                    System.out.println("Car ID " + car.getCarId() + " cannot be deleted completely!");
                }
            } 
            catch (CarNotFoundException ex) 
            {
                System.out.println("An error has occurred while deleting car: " + ex.getMessage() + "\n");
            }
        }
        else
        {
            System.out.println("Car NOT deleted!\n");
        }
    }
}
