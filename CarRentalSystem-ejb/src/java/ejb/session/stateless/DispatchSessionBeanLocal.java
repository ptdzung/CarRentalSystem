/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.OutletEntity;
import entity.TravelDispatchRecordEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import util.exception.InputDataValidationException;
import util.exception.TravelDispatchRecordNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
@Local
public interface DispatchSessionBeanLocal {

    Long createNewTravelDispatchRecord(TravelDispatchRecordEntity dispatchRecord) throws InputDataValidationException, UnknownPersistenceException;

    List<TravelDispatchRecordEntity> retrieveTravelDispatchRecordByDate(Date date, OutletEntity outlet);

    TravelDispatchRecordEntity retrieveTravelDispatchRecordById(Long recordId) throws TravelDispatchRecordNotFoundException;
    
}
