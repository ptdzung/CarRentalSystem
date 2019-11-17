/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrsreservationclient;

import ejb.session.stateful.CarReservationControllerRemote;
import ejb.session.stateless.CarSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.RentalRateSessionBeanRemote;
import entity.CarCategoryEntity;
import entity.CarEntity;
import entity.CarModelEntity;
import entity.OutletEntity;
import entity.OwnCustomerEntity;
import entity.RentalRateEntity;
import entity.RentalRecordEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import util.exception.CarCategoryNotFoundException;
import util.exception.CarModelNotFoundException;
import util.exception.EntityMismatchException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.RentalRateNotFoundException;
import util.exception.RentalRecordNotFoundException;
import util.exception.UnknownPersistenceException;
import util.exception.UsernameExistException;

/**
 *
 * @author Trishpal
 */
public class MainApp {
    
    private CarSessionBeanRemote carSessionBeanRemote;
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    private CarReservationControllerRemote carReservationControllerRemote;
    private RentalRateSessionBeanRemote rentalRateSessionBeanRemote;
    
    private OwnCustomerEntity currentCustomer;

    public MainApp() {
    }

    public MainApp(RentalRateSessionBeanRemote rentalRateSessionBeanRemote, CarSessionBeanRemote carSessionBeanRemote, CustomerSessionBeanRemote customerSessionBean, CarReservationControllerRemote carReservationController) {
        this.rentalRateSessionBeanRemote = rentalRateSessionBeanRemote;
        this.carSessionBeanRemote = carSessionBeanRemote;
        this.customerSessionBeanRemote = customerSessionBean;
        this.carReservationControllerRemote = carReservationController;
    }
    
    public void runApp(){
        Scanner sc = new Scanner(System.in);
        
        while(true){
            System.out.println("*** Welcome to the Merlion Car Reservation System ***\n");
            System.out.println("(1) Register New Customer");
            System.out.println("(2) Customer Login");
            System.out.println("(3) Search Car");
            System.out.println("(4) Exit\n");
            
            System.out.print("> ");
            String response = sc.next();
            
            
            switch(response){
                case "1":
                    registerCustomer();
                    break;
                case "2":
                    try {
                        customerLogin();
                    } catch (InvalidLoginCredentialException ex) {
                        ex.getMessage();
                    }
                    break;
                case "3":
                    searchCar();
                    break;
                case "4":
                    System.out.println("\n*** Exiting System ***");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid command. Please try again!");
            }
        }   
    }
    
    private void registerCustomer(){
        Scanner sc = new Scanner(System.in);
        OwnCustomerEntity c = new OwnCustomerEntity();
        
        System.out.println("*** Reservation System :: Register New Customer ***\n");
        System.out.print("Enter name> ");
        c.setName(sc.nextLine().trim());
        System.out.print("Enter username> ");
        c.setUsername(sc.nextLine().trim());
        System.out.print("Enter password> ");
        c.setPassword(sc.nextLine().trim());
        System.out.print("Enter email address> ");
        c.setEmail(sc.nextLine().trim());
        
        try {
            Long cusId = customerSessionBeanRemote.createNewOwnCustomer(c);
            System.out.println("New customer created successfully. Customer ID: " + cusId);
        } catch (InputDataValidationException | UnknownPersistenceException ex) {
            System.err.println("\n" + ex.getMessage());
        }
    }
    
    private void customerLogin() throws InvalidLoginCredentialException {
        Scanner sc = new Scanner(System.in);
        
        String username = "";
        String password = "";
        
        System.out.println("*** Reservation System :: Login ***\n");
        System.out.print("Enter username> ");
        username = sc.nextLine().trim();
        System.out.print("Enter password> ");
        password = sc.nextLine().trim();
     
        if(username.length() > 0 && password.length() > 0) {
            try{
                currentCustomer = carReservationControllerRemote.customerLogin(username, password);
                System.out.println("\nLogin Successful");
                customerMenu();
            }catch(InvalidLoginCredentialException e){
                System.err.println("\n" + e.getMessage());
            }
        } else {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
    
    private void customerMenu(){
        Scanner sc = new Scanner(System.in);
        
        while(true){
            System.out.println("*** Merlion Car Reservation System ***");
            System.out.println("(1) Search - Reserve Car");
            System.out.println("(2) Cancel Reservation");
            System.out.println("(3) View All My Reservations");
            System.out.println("(4) Logout\n");
            
            System.out.print("> ");
            String response = sc.next();
            
            switch(response){
                case "1":
                    searchCar();
                    break;
                    
                case "2":
                    cancelReservation();
                    break;
                
                case "3":
                    viewAllReservations();
                    break;
                    
                case "4":
                    customerLogOut();
                    return;
                    
                default:
                    System.err.println("Please enter a valid command!");
            }
        }
    }
    
    private void searchCar(){
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("*** Reservation System :: Search Car ***\n");
            System.out.print("Enter car category: ");
            CarCategoryEntity category = carSessionBeanRemote.retrieveCarCategoryByName(sc.nextLine().trim());
            
            CarModelEntity model = null;
            System.out.print("Enter car model make (blank if no preference): ");
            String make = sc.nextLine().trim();
            if (make.length() > 0) {
                System.out.print("Enter car model name: ");
                model = carSessionBeanRemote.retrieveCarModel(make, sc.nextLine().trim());
            }
            System.out.print("Enter pickup date (Input format: year month day hour minute): ");
            Date startDate = new Date(sc.nextInt()-1900, sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt());

            Date endDate;
            while(true) {
                System.out.print("Enter return date (Input format: year month day hour minute): ");
                endDate = new Date(sc.nextInt()-1900, sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt());
                if (endDate.getYear() != startDate.getYear() || endDate.getMonth() != startDate.getMonth() || endDate.getDay() != startDate.getDay()) {
                    if (endDate.getTime() >= startDate.getTime()) {
                        break;
                    } else {
                        System.out.println("Invalid input, please try again!\n");
                    }
                } else {
                    System.out.println("Invalid input, please try again!\n");
                }
            }
            
            OutletEntity pickupOutlet, returnOutlet;
            while(true)
            {
                System.out.print("Select Pickup Outlet (1: A, 2: B, 3: C)> ");
                Long i = sc.nextLong();
            
                if(i >= 1 && i <= 3)
                {
                    pickupOutlet = carSessionBeanRemote.retrieveOutletById(i);
                    if (pickupOutlet.getOpenTime() == null || (startDate.getHours()*60 + startDate.getMinutes()) >= (pickupOutlet.getOpenTime().getHours()*60 + pickupOutlet.getOpenTime().getMinutes())) {
                        break;
                    } else {
                        System.out.println("This outlet is not opened at the designated pick up time!");
                    }
                }
                else
                {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
            while(true)
            {
                System.out.print("Select Return Outlet (1: A, 2: B, 3: C)> ");
                Long i = sc.nextLong();
            
                if(i >= 1 && i <= 3)
                {
                    returnOutlet = carSessionBeanRemote.retrieveOutletById(i);
                    if (returnOutlet.getCloseTime() == null || (startDate.getHours()*60 + startDate.getMinutes()) <= (returnOutlet.getCloseTime().getHours()*60 + returnOutlet.getCloseTime().getMinutes())) {
                        break;
                    } else {
                        System.out.println("This outlet is not opened at the designated return time!");
                    }
                    break;
                }
                else
                {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
            sc.nextLine();
            
            
            List<CarEntity> availableCars = carReservationControllerRemote.searchCar(category, model, startDate, endDate, pickupOutlet);
            BigDecimal totalAmount = new BigDecimal("0");
            if (availableCars.isEmpty()) {
                System.out.println("There is no available car!\n");
            } else {
                List<RentalRateEntity> rates = carReservationControllerRemote.retrieveListOfOptimalRates(category, startDate, endDate);
                for (RentalRateEntity r : rates) {
                    totalAmount.add(r.getRatePerDay());
                }
                System.out.println("Total Amount: " + totalAmount.toString() + "\n");
                System.out.printf("%15s%20s%20s\n", "License Plate", "Make", "Model");
                for(CarEntity car : availableCars) {
                    System.out.printf("%15s%20s%20s\n", car.getLicensePlate(), car.getCarModel().getMake(), car.getCarModel().getModelName());
                }
            }
            
            if (currentCustomer != null) {
                System.out.print("Do you want to reserve a car? (Enter 'Y' to reserve): ");
                String input = sc.nextLine().trim();
                if (input.equals("Y")) {
                    reserveCar(category, model, startDate, endDate, pickupOutlet, returnOutlet, totalAmount);
                } else {
                    System.out.println("Returning to Reservation System Homepage.....\n");
                }
            }
        } catch (CarCategoryNotFoundException | CarModelNotFoundException | RentalRateNotFoundException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }
    
    private void reserveCar(CarCategoryEntity category, CarModelEntity model, Date startDate, Date endDate, OutletEntity pickupOutlet, OutletEntity returnOutlet, BigDecimal totalAmount) {
        Scanner sc = new Scanner(System.in);
        System.out.println("*** Reservation System :: Reserve Car ***\n");
        System.out.print("Enter Credit Card Number: ");
        String creditCardNumber = sc.nextLine();
        
        System.out.print("Would you like to pay now or on pickup? (Enter 'Y' to pay now): ");
        String input = sc.nextLine().trim();
        Boolean hasPaid = false;
        if (input.equals("Y")) {
            hasPaid = true;
        }
        
        Long newRentalId = carReservationControllerRemote.createNewReservation(creditCardNumber, hasPaid, category, model, startDate, endDate, pickupOutlet, returnOutlet, totalAmount);
        
        if (newRentalId == -1l) {
            System.out.println("System cannot create a new reservation!");
        } else {
            System.out.println("New Rental Record ID " + newRentalId + " is created successfully!");
        }
    }
    
    
    private void cancelReservation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("*** Reservation System :: Cancel Reservation ***\n");
        
        String input;
        
        System.out.print("Enter Reservation ID> ");
        Long resId = new Long(sc.nextInt()); 
        viewReservationDetail(resId);
        
        System.out.printf("Confirm cancel reservation (Reservation ID: %d )(Enter 'Y' to delete)> ", resId);
        input = sc.nextLine().trim();
            
        if(input.equals("Y"))
        {
            try
            {
                String message = carReservationControllerRemote.cancelReservation(resId);
                System.out.println("Reservation cancelled successfully!\n");
                System.out.println(message);
            }
            catch (RentalRecordNotFoundException ex) 
            {
                System.out.println("An error has occurred while cancelling the reservation: " + ex.getMessage() + "\n");
            }
        }
        else
        {
            System.out.println("Reservation NOT deleted!\n");
        }

    }
           
    
    
    private void viewReservationDetail(Long resId){       
        try {
            System.out.println("\n****View Reservation Details****");
            String details = carReservationControllerRemote.retrieveReservationDetails(resId);
            System.out.println(details);
        } catch (RentalRecordNotFoundException | EntityMismatchException ex) {
            System.err.println(ex.getMessage());
        }
             
    }
    
    private void viewAllReservations(){
        Scanner sc = new Scanner(System.in);
        System.out.println("*** Reservation System :: View All Reservations ***\n");
        List<RentalRecordEntity> reservations = carReservationControllerRemote.retrieveAllReservation();
        if(reservations.isEmpty()){
            System.out.println("No reservation records available.\n");
        }
        
        for(RentalRecordEntity r: reservations){
            System.out.println( "Reservation ID: " + r.getRentalRecordId().toString() + "\n" +
                                "Car: " + r.getCar() + "\n" +
                                "Rented From: " + r.getRentedFrom().toString() + "\n" +
                                "Rented To: " + r.getRentedTo().toString()  + "\n" +
                                "Total Amount: $" + r.getTotalAmount());
            System.out.println();
        }
        
        System.out.println("Press any key to continue.....");
        sc.nextLine();
    }
    
    private void customerLogOut() {
        carReservationControllerRemote.customerLogout();
        System.out.println("****Logout successful****\n");
    }
    
    

}