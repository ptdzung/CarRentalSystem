/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrsreservationclient;

import ejb.session.stateful.CarReservationControllerRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Trishpal
 */
public class MainApp {
    
    private CustomerSessionBeanRemote customerSessionBean;
    private CarReservationControllerRemote carReservationController;
    
    private boolean loggedIn;
    private Scanner sc = new Scanner(System.in);

    public MainApp() {
    }

    public MainApp(CustomerSessionBeanRemote customerSessionBean, CarReservationControllerRemote carReservationController) {
        this.customerSessionBean = customerSessionBean;
        this.carReservationController = carReservationController;
    }
    
    public void runApp(){
        
        while(true){
            System.out.println("*Welcome to the Car Reservation client*");
            System.out.println("(1) Register as Customer");
            System.out.println("(2) Customer Login");
            System.out.println("(3) Search car");
            System.out.println("(4) Exit");
            
            String response = sc.next();
            switch(response){
                case "1":
                    registerCustomer();
                    break;
                case "2":
                    customerLogin();
                    break;
                case "3":
                    searchCar();
                    break;
                case "4":
                    System.out.println("\n***Exiting system*");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid command");
            }
        }   
    }
    
    private void registerCustomer(){
        System.out.println("*Register New Customer*");
        System.out.println("Enter name");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("Enter username");
        String username = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        System.out.println("Enter email address");
        String email = sc.next();
        
        customerSessionBean.registerNewCustomer(name, username, password, email);
        System.out.println("\n***New Customer Created!*\n");
    }
    
    private void customerLogin(){
        System.out.println("\n***Customer Login*");
        System.out.println("Enter username");
        String username = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        
        try{
            carReservationController.customerLogin(username, password);
            System.out.println("\nLogin Successful");
            loggedIn = true;
            customerMenu();
        }catch(InvalidLoginCredentialException e){
            System.err.println("\n" + e.getMessage());
        }
    }
    
    private void customerMenu(){
        
        while(true){
            System.out.println("\n***Welcome to the Car Reservation client*");
            System.out.println("(1) Search/Reserve car");
            System.out.println("(2) Cancel Reservation");
            System.out.println("(3) View Reservation Details");
            System.out.println("(4) View All My Reservations");
            System.out.println("(5) Log Out");
            String response = sc.next();
            switch(response){
                case "1":
                    searchCar();
                    break;
                    
                case "2":
                    cancelReservation();
                    break;
                    
                case "3":
                    viewReservationDetail();
                    break;
                
                case "4":
                    viewAllReservations();
                    break;
                    
                case "5":
                    customerLogOut();
                    return;
                    
                default:
                    System.err.println("Please enter a valid command");
            }
        }
    }
    
    private void searchCar(){
        
     
    }
    /*
    private void reserveCar(ReservationTicket ticket){
        for(int i = 0; i < ticket.getAvailableRoomTypes().size(); i++){
            RoomTypeEntity type = ticket.getAvailableRoomTypes().get(i);
            System.out.println("Enter number of " + type.getTypeName() + " to reserve:");
            int num = sc.nextInt();
            if(num < 0 || num > ticket.getRespectiveNumberOfRoomsRemaining().get(i)){
                ticket.getRespectiveNumberReserved().add(0);
                System.out.println("*Invalid Number, 0 rooms of this type will be reserved*\n");
            }else{
                ticket.getRespectiveNumberReserved().add(num);
                System.out.println("*" + num + " of " + type.getTypeName() + " added to cart*\n");
            }
        }
        
        roomReservationController.reserveRoom(ticket);
        System.out.println("Reservation Successful.");
    }
    */
    
    private void cancelReservation(){
        /*
        for(int i = 0; i < ticket.getAvailableRoomTypes().size(); i++){
            RoomTypeEntity type = ticket.getAvailableRoomTypes().get(i);
            System.out.println("Enter number of " + type.getTypeName() + " to reserve:");
            int num = sc.nextInt();
            if(num < 0 || num > ticket.getRespectiveNumberOfRoomsRemaining().get(i)){
                ticket.getRespectiveNumberReserved().add(0);
                System.out.println("*Invalid Number, 0 rooms of this type will be reserved*\n");
            }else{
                ticket.getRespectiveNumberReserved().add(num);
                System.out.println("*" + num + " of " + type.getTypeName() + " added to cart*\n");
            }
        }
        
        roomReservationController.reserveRoom(ticket);
        System.out.println("Reservation Successful.");
        */
    }
    
    private void viewReservationDetail(){
        /*
        System.out.println("Enter reservation ID");
        Long resId = new Long(sc.nextInt());
        try {
            System.out.println("\n***View Reservation Detail*");
            String details = carReservationController.retrieveReservationDetails(resId);
            System.out.println(details);
        } catch (RentalRecordNotFoundException | EntityMismatchException ex) {
            System.err.println(ex.getMessage());
        }
        
       */ 
    }
    
    private void viewAllReservations(){
        /*
        System.out.println("\n***Viewing all Reservation Records*");
        ArrayList<RentalRecordEntity> reservations = carReservationController.retrieveAllReservation();
        if(reservations.isEmpty()){
            System.err.println("\nNo reservation records available.\n");
            return;
        }
        
        for(RentalRecordEntity r: reservations){
            System.out.println( "Reservation ID: " + r.getRentalRecordId().toString() + "\n" +
                                "Start Date: " + r.getStartDateAsString() + "\n" +
                                "End Date: " + r.getEndDateAsString() + "\n" +
                                "Room Type Reserved: " + r.getRoomType().getTypeName() + "\n" +
                                "Bill: $" + r.getBill());
            System.out.println();
        }
        System.out.println("*End of Reservation Records*\n");
        */
    }
    
    private void customerLogOut() {
        carReservationController.customerLogout();
        loggedIn = false;
        System.out.println("\n***Logout successful*\n");
    }

    private CustomerSessionBeanRemote lookupCustomerSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (CustomerSessionBeanRemote) c.lookup("java:comp/env/CustomerSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
