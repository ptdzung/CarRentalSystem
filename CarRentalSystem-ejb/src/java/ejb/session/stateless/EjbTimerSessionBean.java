/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CarCategoryEntity;
import entity.CarModelEntity;
import entity.OutletEntity;
import entity.RentalRecordEntity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author Pham The Dzung
 */
@Stateless
public class EjbTimerSessionBean implements EjbTimerSessionBeanRemote, EjbTimerSessionBeanLocal {

    @EJB
    private CarSessionBeanLocal carSessionBeanLocal;

    @EJB
    private ReservationRecordSessionBeanLocal reservationRecordSessionBeanLocal;
    
    @Schedule(hour = "2", info = "carAllocation")
    public void carAllocation() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("********** EjbTimerSession.carAllocation(): Timeout at " + timeStamp);
        
        Calendar calendar = Calendar.getInstance();
        Date curr = calendar.getTime();
        List<RentalRecordEntity> rentalRecords = reservationRecordSessionBeanLocal.retrieveRentalRecordByDate(curr);
        
        if (rentalRecords.isEmpty()) {
            System.out.println("There is no record for today!");
        } else {
            for(RentalRecordEntity r: rentalRecords) {
                r.setCar(carSessionBeanLocal.retrieveCarForAllocation(r));
            }
            System.out.println("Car allocation and travel dispatch record generated successfully!");
        }
    }
    
}
