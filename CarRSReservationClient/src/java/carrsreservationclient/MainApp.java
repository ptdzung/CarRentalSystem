/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrsreservationclient;

import ejb.session.stateful.CarReservationControllerRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import entity.OwnCustomerEntity;
import entity.RentalRecordEntity;
import java.util.List;
import java.util.Scanner;
import util.exception.EntityMismatchException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.RentalRecordNotFoundException;
import util.exception.UnknownPersistenceException;
import util.exception.UsernameExistException;

/**
 *
 * @author Trishpal
 */
public class MainApp {
    
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    private CarReservationControllerRemote carReservationControllerRemote;
    
    private OwnCustomerEntity currentCustomer;

    public MainApp() {
    }

    public MainApp(CustomerSessionBeanRemote customerSessionBean, CarReservationControllerRemote carReservationController) {
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
        
        System.out.println("*** Reservation System :: Register New Customer ***\n");
        System.out.print("Enter name> ");
        String name = sc.nextLine().trim();
        System.out.print("Enter username> ");
        String username = sc.nextLine().trim();
        System.out.print("Enter password> ");
        String password = sc.nextLine().trim();
        System.out.print("Enter email address> ");
        String email = sc.nextLine().trim();
        
        try {
            Long cusId = customerSessionBeanRemote.registerNewCustomer(name, username, password, email);
            System.out.println("New customer created successfully. Customer ID: " + cusId);
        } catch (UsernameExistException | InputDataValidationException | UnknownPersistenceException ex) {
            ex.getMessage();
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
            System.out.println("(1) Search/Reserve Car");
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
                    System.err.println("Please enter a valid command");
            }
        }
    }
    
    private void searchCar(){
        
      /*
      Scanner sc = new Scanner(System.in);
        
      try {
            System.out.println("\n****Search Car*****");
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Enter  in date (dd/mm/yyyy):");
            Date startDate = df.parse(sc.next());
            System.out.println("Enter check out date (dd/mm/yyyy):");
            Date endDate = df.parse(sc.next());
            
            if(startDate.after(endDate) || startDate.equals(endDate)){
                System.err.println("\nCheck in date must be after checkout date.\n");
                return null;
            }
            
            ReservationTicket ticket = roomReservationController.searchRooms(startDate, endDate, false);
            if(ticket.getAvailableRoomTypes().isEmpty()){
                System.err.println("\nThere are no available rooms for your desired check in and check out date.\n");
                return null;
            }
            System.out.println("\n***Available Rooms*");
            for(int i = 0; i < ticket.getAvailableRoomTypes().size(); i++){
                RoomTypeEntity type = ticket.getAvailableRoomTypes().get(i);
                System.out.println("(" + i + ")" + type.getTypeName());
                System.out.println(type.getDescription());
                System.out.println("Amenities: " + type.getAmenities());
                System.out.println("Capacity: " + type.getCapacity());
                System.out.println("Rooms Available: " + ticket.getRespectiveNumberOfRoomsRemaining().get(i) );
                System.out.println("Cost: " + ticket.getRespectiveTotalBill().get(i));
                System.out.println();
            }
            System.out.println("\n****End of list****\n");
            while(loggedIn){
                System.out.println("Reserve a room? (Y/N)");
                String resp = sc.next();
                switch(resp){
                    case "Y":
                        reserveHotelRoom(ticket);
                        return ticket;
                    case "N":
                        return null;
                        
                    default:
                        System.out.println("Please choose a valid option");
                        break;
                }
            }
            return ticket;
        } catch (ParseException ex) {
            System.err.println("\nPlease enter valid date format\n");
            return null;
        }
        */
    }
    
    private void reserveCar(){
        /*
        Scanner sc = new Scanner(System.in);
        
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
            System.out.println("\nNo reservation records available.\n");
        }
        
        for(RentalRecordEntity r: reservations){
            System.out.println( "Reservation ID: " + r.getRentalRecordId().toString() + "\n" +
                                "Car: " + r.getCar() + "\n" +
                                "Rented From: " + r.getRentedFrom() + "\n" +
                                "Rented To: " + r.getRentedTo() + "\n" +
                                "Total Amount: $" + r.getTotalAmount());
            System.out.println();
        }
        
        System.out.println("Press any key to continue.....");
        sc.nextLine();
    }
    
    private void customerLogOut() {
        carReservationControllerRemote.customerLogout();
        System.out.println("\n****Logout successful****\n");
    }

}