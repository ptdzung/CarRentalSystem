/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CarCategoryEntity;
import entity.RentalRateEntity;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.enumerator.StatusEnum;
import util.exception.CarCategoryNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.RentalRateNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
@Stateless
@Local(RentalRateSessionBeanLocal.class)
@Remote(RentalRateSessionBeanRemote.class)
public class RentalRateSessionBean implements RentalRateSessionBeanRemote, RentalRateSessionBeanLocal {

    @EJB
    private CarSessionBeanLocal carSessionBeanLocal;

    @PersistenceContext(unitName = "CarRentalManagementSystem-ejbPU")
    private EntityManager em;
    
    
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public RentalRateSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    

    @Override
    public Long createNewRentalRate(RentalRateEntity rentalRate) throws InputDataValidationException, UnknownPersistenceException {
        try
        {
            Set<ConstraintViolation<RentalRateEntity>>constraintViolations = validator.validate(rentalRate);
        
            if(constraintViolations.isEmpty())
            {
                em.persist(rentalRate);
                em.flush();

                return rentalRate.getRentalRateId();
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsForRentalRate(constraintViolations));
            }            
        }
        catch(PersistenceException ex) {
                throw new UnknownPersistenceException(ex.getMessage());
        }
    }
    
    private String prepareInputDataValidationErrorsForRentalRate(Set<ConstraintViolation<RentalRateEntity>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }

    @Override
    public RentalRateEntity retrieveRentalRate(String rateName, String categoryName) throws RentalRateNotFoundException, CarCategoryNotFoundException {
        CarCategoryEntity carCategory = carSessionBeanLocal.retrieveCarCategoryByName(categoryName);
        
        if (carCategory != null) {
            Query query = em.createQuery("SELECT rr FROM RentalRateEntity rr WHERE rr.rateName = :inRateName AND rr.carCategory = :inRRCategory");
            query.setParameter("inRateName", rateName);
            query.setParameter("inRRCategory", carCategory);
            
            try {
                return (RentalRateEntity)query.getSingleResult();
            } catch (NoResultException | NonUniqueResultException ex) {
                throw new RentalRateNotFoundException("Rental Rate " + rateName + " does not exist!");
            }
        } else {
            throw new CarCategoryNotFoundException("Car Category " + categoryName + " does not exist!");
        }
    }
    
    
    @Override
    public void updateRentalRate(RentalRateEntity rate) throws RentalRateNotFoundException, InputDataValidationException {
        if(rate != null && rate.getRentalRateId()!= null)
        {
            Set<ConstraintViolation<RentalRateEntity>>constraintViolations = validator.validate(rate);
        
            if(constraintViolations.isEmpty())
            {
                RentalRateEntity rentalRate = retrieveRentalRateById(rate.getRentalRateId());
                
                rentalRate.setRateName(rate.getRateName());
                rentalRate.setRatePerDay(rate.getRatePerDay());
                rentalRate.setStartDate(rate.getStartDate());
                rentalRate.setEndDate(rate.getEndDate());
                rentalRate.setStatus(rate.getStatus());
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsForRentalRate(constraintViolations));
            }
        }
        else
        {
            throw new RentalRateNotFoundException("Car ID is not provided for car to be updated");
        }
    }

    @Override
    public RentalRateEntity retrieveRentalRateById(Long rentalRateId) throws RentalRateNotFoundException {
        RentalRateEntity rate = em.find(RentalRateEntity.class, rentalRateId);
        
        if(rate != null) {
            return rate;
        } else {
            throw new RentalRateNotFoundException("Rental Rate ID " + rentalRateId + " does not exist!");
        }
    }
    
    @Override
    public boolean deleteRentalRate(Long rentalRateId) throws RentalRateNotFoundException {
        RentalRateEntity rate = em.find(RentalRateEntity.class, rentalRateId);
        if (rate != null) {
            CarCategoryEntity carCategory = rate.getCarCategory();
            if (rate.getRentalDays().isEmpty()) {
                carCategory.removeRentalRate(rate);
                em.remove(rate);
                return true;
            } else {
                rate.setStatus(StatusEnum.DISABLED);
                return false;
            }
        } else {
            throw new RentalRateNotFoundException("Rental Rate ID " + rentalRateId + " does not exist!");
        }
    }

    @Override
    public List<RentalRateEntity> retrieveAllRentalRates() {
        Query query = em.createQuery("SELECT rr FROM RentalRateEntity rr");
        
        return query.getResultList();
    }
    
    
}
