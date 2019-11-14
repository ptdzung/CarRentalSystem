/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrsmanagementclient;

import ejb.session.stateless.CarSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.RentalRateSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author Pham The Dzung
 */
public class Main {

    @EJB
    private static EmployeeSessionBeanRemote employeeSessionBeanRemote;

    @EJB
    private static RentalRateSessionBeanRemote rentalRateSessionBeanRemote;

    @EJB
    private static CarSessionBeanRemote carSessionBeanRemote;
    

    
    public static void main(String[] args) {
        MainApp mainApp = new MainApp(carSessionBeanRemote, rentalRateSessionBeanRemote, employeeSessionBeanRemote);
        mainApp.runApp();
    }
    
}
