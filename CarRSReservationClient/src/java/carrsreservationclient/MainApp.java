/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrsreservationclient;

import ejb.session.stateful.CarReservationControllerRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import entity.RentalRecordEntity;
import java.util.List;
import java.util.Scanner;
import util.exception.EntityMismatchException;
import util.exception.InvalidLoginCredentialException;
import util.exception.RentalRecordNotFoundException;

/**
 *
 * @author Trishpal
 */
public class MainApp {
    
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    private CarReservationControllerRemote carReservationControllerRemote;
    
    private boolean loggedIn;
    private Scanner sc = new Scanner(System.in);

    public MainApp() {
    }

    public MainApp(CustomerSessionBeanRemote customerSessionBean, CarReservationControllerRemote carReservationController) {
        this.customerSessionBeanRemote = customerSessionBean;
        this.carReservationControllerRemote = carReservationController;
    }
    
    public void runApp(){
        
        while(true){
            System.out.println("****Welcome to the Car Reservation client****");
            System.out.println("(1) Register as Customer");
            System.out.println("(2) Customer Login");
            System.out.println("(3) Search car");
            System.out.println("(4) Exit");
            
            System.out.print("> ");
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
                    System.out.println("\n****Exiting system****");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid command");
            }
        }   
    }
    
    private void registerCustomer(){
        System.out.println("****Register New Customer****");
        System.out.print("Enter name> ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter username> ");
        String username = sc.next();
        System.out.print("Enter password> ");
        String password = sc.next();
        System.out.print("Enter email address> ");
        String email = sc.next();
        
        customerSessionBeanRemote.registerNewCustomer(name, username, password, email);
        System.out.println("\n***New Customer Created!*\n");
    }
    
    private void customerLogin(){
        System.out.println("\n****Customer Login****");
        System.out.print("Enter username> ");
        String username = sc.next();
        System.out.print("Enter password> ");
        String password = sc.next();
     
        try{
            carReservationControllerRemote.customerLogin(username, password);
            System.out.println("\nLogin Successful");
            loggedIn = true;
            customerMenu();
        }catch(InvalidLoginCredentialException e){
            System.err.println("\n" + e.getMessage());
        }
    }
    
    private void customerMenu(){
        
        while(true){
            System.out.println("****Welcome to the Car Reservation client****");
            System.out.println("(1) Search/Reserve car");
            System.out.println("(2) Cancel Reservation");
            System.out.println("(3) View Reservation Details");
            System.out.println("(4) View All My Reservations");
            System.out.println("(5) Log Out");
            
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
      /*
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
        
        Scanner scanner = new Scanner(System.in);
        String input;
        
        System.out.print("Enter reservation ID> ");
        Long resId = new Long(sc.nextInt()); 
        System.out.println("\n****Cancel Reservation****");
        System.out.printf("Confirm cancel Reservation (Reservation ID: %d )(Enter 'Y' to delete)> ", resId);
        input = scanner.nextLine().trim();
            
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
           
    
    
    private void viewReservationDetail(){       
        System.out.print("Enter reservation ID> ");
        Long resId = new Long(sc.nextInt());
        try {
            System.out.println("\n****View Reservation Details****");
            String details = carReservationControllerRemote.retrieveReservationDetails(resId);
            System.out.println(details);
        } catch (RentalRecordNotFoundException | EntityMismatchException ex) {
            System.err.println(ex.getMessage());
        }
             
    }
    
    private void viewAllReservations(){
        try {
        System.out.println("\n****Viewing all Reservation Records****");
        List<RentalRecordEntity> reservations = carReservationControllerRemote.retrieveAllReservation();
        if(reservations.isEmpty()){
            System.err.println("\nNo reservation records available.\n");
            return;
        }
        
        for(RentalRecordEntity r: reservations){
            System.out.println( "Reservation ID: " + r.getRentalRecordId().toString() + "\n" +
                                "Car: " + r.getCar() + "\n" +
                                "Rented From: " + r.getRentedFrom() + "\n" +
                                "Rented To: " + r.getRentedTo() + "\n" +
                                "Total Amount: $" + r.getTotalAmount());
            System.out.println();
        }
        System.out.println("****End of Reservation Records****\n");
       } catch (RentalRecordNotFoundException | EntityMismatchException ex) {
            System.err.println(ex.getMessage());
        } 
    }
    
    private void customerLogOut() {
        carReservationControllerRemote.customerLogout();
        loggedIn = false;
        System.out.println("\n****Logout successful****\n");
    }

}
