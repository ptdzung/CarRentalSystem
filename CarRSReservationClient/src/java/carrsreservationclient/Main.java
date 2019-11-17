
package carrsreservationclient;

import ejb.session.stateful.CarReservationControllerRemote;
import ejb.session.stateless.CarSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.RentalRateSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author Trishpal
 */
public class Main {

    @EJB(name = "RentalRateSessionBeanRemote")
    private static RentalRateSessionBeanRemote rentalRateSessionBeanRemote;

    @EJB(name = "CarSessionBeanRemote")
    private static CarSessionBeanRemote carSessionBeanRemote;
    
    @EJB(name = "CustomerSessionBeanRemote")
    private static CustomerSessionBeanRemote customerSessionBeanRemote;

    @EJB(name = "CarReservationControllerRemote")
    private static CarReservationControllerRemote carReservationControllerRemote;
  
    
    public static void main(String[] args) {
        MainApp mainApp = new MainApp(rentalRateSessionBeanRemote, carSessionBeanRemote,customerSessionBeanRemote,carReservationControllerRemote);
        mainApp.runApp();
    }
    
}