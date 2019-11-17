/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holidayreservationsystemjavaseclient;


import java.util.List;
import java.util.Scanner;
import ws.client.EntityMismatchException_Exception;
import ws.client.InvalidLoginCredentialException_Exception;
import ws.client.RentalRecordNotFoundException_Exception;
import ws.client.PartnerEntity;
import ws.client.RentalRecordEntity;

/**
 *
 * @author Trishpal
 */

public class MainApp {
       
    private PartnerEntity currentPartner;
    
    public void runApp() throws InvalidLoginCredentialException_Exception, RentalRecordNotFoundException_Exception, EntityMismatchException_Exception {
        
        Scanner sc = new Scanner(System.in);
              
          while(true){
            System.out.println("*** Welcome to the Holiday Reservation System ***\n");
            System.out.println("(1) Login");
            System.out.println("(2) Search Car");
            System.out.println("(3) Exit\n");
            
            System.out.print("> ");
            String response = sc.next();            
            
            switch(response){
                case "1":                         
                       partnerLogin();
                       System.out.println("Login successful!\n");                
                       partnerMenu();
                    break;
                case "2":
                    //searchCar();
                    break;
                case "3":
                    System.out.println("\n*** Exiting System ***");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid command. Please try again!");
            }
        }
    }
    
    private void partnerLogin() throws InvalidLoginCredentialException_Exception{
        Scanner sc = new Scanner(System.in);
        String username = "";
        String password = "";
        
        System.out.println("*** Holiday Reservation System :: Login ***\n");
        System.out.print("Enter username> ");
        username = sc.nextLine().trim();
        System.out.print("Enter password> ");
        password = sc.nextLine().trim();
                   
        currentPartner = partnerLogin(username, password);            
        
    }      
         
    private void partnerMenu() throws RentalRecordNotFoundException_Exception, EntityMismatchException_Exception{
        
        Scanner sc = new Scanner(System.in);
        
        while(true){
            System.out.println("*** Holiday Reservation System ***");
            System.out.println("(1) Search/Reserve Car");
            System.out.println("(2) Cancel Reservation");
            System.out.println("(3) View All My Reservations");
            System.out.println("(4) Logout\n");
            
            System.out.print("> ");
            String response = sc.next();
            
            switch(response){
                case "1":
                    //searchCar();
                    break;                  
                case "2":
                    cancelReservation();
                    break;                
                case "3":
                    viewAllReservations();
                    break;                  
                case "4":
                    partnerLogOut();
                    break;             
                default:
                    System.err.println("Please enter a valid command");
            }
        }
    }
    
    private void cancelReservation() throws RentalRecordNotFoundException_Exception, EntityMismatchException_Exception{
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
            String message = cancelReservation(resId);
            System.out.println("Reservation cancelled successfully!\n");
            System.out.println(message);
        }
        else
        {
            System.out.println("Reservation NOT deleted!\n");
        }

    }
    
    private void viewReservationDetail(Long resId) throws RentalRecordNotFoundException_Exception, EntityMismatchException_Exception{       
            System.out.println("\n****View Reservation Details****");
            String details = retrievePartnerReservationDetails(resId, currentPartner.getPartnerId());
            System.out.println(details);               
    }
    
    private void viewAllReservations(){
        Scanner sc = new Scanner(System.in);
        System.out.println("*** Reservation System :: View All Reservations ***\n");
        List<RentalRecordEntity> reservations = retrieveRentalRecordsByPartner(currentPartner);
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
    
    private void partnerLogOut() {
       // currentPartner = null;
        System.out.println("\n***Logout successful***\n");
    }

    private static ws.client.PartnerEntity partnerLogin(java.lang.String username, java.lang.String password) throws InvalidLoginCredentialException_Exception {
        ws.client.PartnerWebService_Service service = new ws.client.PartnerWebService_Service();
        ws.client.PartnerWebService port = service.getPartnerWebServicePort();
        return port.partnerLogin(username, password);
    }

    private static String cancelReservation(long resId) throws RentalRecordNotFoundException_Exception {
        ws.client.PartnerWebService_Service service = new ws.client.PartnerWebService_Service();
        ws.client.PartnerWebService port = service.getPartnerWebServicePort();
        return port.cancelReservation(resId);
    }

    private static String retrievePartnerReservationDetails(long resId, long partnerId) throws RentalRecordNotFoundException_Exception, EntityMismatchException_Exception {
        ws.client.PartnerWebService_Service service = new ws.client.PartnerWebService_Service();
        ws.client.PartnerWebService port = service.getPartnerWebServicePort();
        return port.retrievePartnerReservationDetails(resId, partnerId);
    }

    private static java.util.List<ws.client.RentalRecordEntity> retrieveRentalRecordsByPartner(ws.client.PartnerEntity part) {
        ws.client.PartnerWebService_Service service = new ws.client.PartnerWebService_Service();
        ws.client.PartnerWebService port = service.getPartnerWebServicePort();
        return port.retrieveRentalRecordsByPartner(part);
    }
    
    
          
}


