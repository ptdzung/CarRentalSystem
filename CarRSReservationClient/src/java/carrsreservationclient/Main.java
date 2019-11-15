
package carrsreservationclient;

import ejb.session.stateful.CarReservationControllerRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author Trishpal
 */
public class Main {

    @EJB(name = "CustomerSessionBeanRemote")
    private static CustomerSessionBeanRemote customerSessionBeanRemote;

    @EJB(name = "CarReservationControllerRemote")
    private static CarReservationControllerRemote carReservationControllerRemote;
  
    
    public static void main(String[] args) {
        MainApp mainApp = new MainApp(customerSessionBeanRemote,carReservationControllerRemote);
        mainApp.runApp();
    }
    
}