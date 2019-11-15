/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.OutletEntity;
import entity.TravelDispatchRecordEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.InputDataValidationException;
import util.exception.TravelDispatchRecordNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
@Stateless
public class DispatchSessionBean implements DispatchSessionBeanRemote, DispatchSessionBeanLocal {

    @PersistenceContext(unitName = "CarRentalManagementSystem-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public DispatchSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public Long createNewTravelDispatchRecord(TravelDispatchRecordEntity dispatchRecord) throws InputDataValidationException, UnknownPersistenceException {
        try
        {
            Set<ConstraintViolation<TravelDispatchRecordEntity>>constraintViolations = validator.validate(dispatchRecord);
        
            if(constraintViolations.isEmpty())
            {
                em.persist(dispatchRecord);
                em.flush();

                return dispatchRecord.getTravelDispatchRecordId();
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsForDispatch(constraintViolations));
            }            
        }
        catch(PersistenceException ex) {
                throw new UnknownPersistenceException(ex.getMessage());
        }
    }
    
    private String prepareInputDataValidationErrorsForDispatch(Set<ConstraintViolation<TravelDispatchRecordEntity>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }

    @Override
    public List<TravelDispatchRecordEntity> retrieveTravelDispatchRecordByDate(Date date, OutletEntity outlet) {
        Query query = em.createQuery("SELECT dr FROM TravelDispatchRecordEntity dr WHERE dr.receivingOutlet = :inOutlet");
        query.setParameter("inOutlet", outlet);
        List<TravelDispatchRecordEntity> list = query.getResultList();
        List<TravelDispatchRecordEntity> dayList = new ArrayList<>();
        Date temp;
        
        for(TravelDispatchRecordEntity r : list) {
            temp = r.getRentalRecord().getRentedFrom();
            if(date.getYear() == temp.getYear()) {
                if(date.getMonth() == temp.getMonth()) {
                    if(date.getDay() == temp.getDay()) {
                        dayList.add(r);
                    }
                }
            }
        }
        
        return dayList;
    }

    @Override
    public TravelDispatchRecordEntity retrieveTravelDispatchRecordById(Long recordId) throws TravelDispatchRecordNotFoundException {
        TravelDispatchRecordEntity record = em.find(TravelDispatchRecordEntity.class, recordId);
        if (record != null) {
            return record;
        } else {
            throw new TravelDispatchRecordNotFoundException("Dispatch Record ID " + recordId + " does not exist!");
        }
    }
    
    
    
}
