/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.RentalRateEntity;
import java.util.Set;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.InputDataValidationException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
@Stateless
@Local(RentalRateSessionBeanLocal.class)
@Remote(RentalRateSessionBeanRemote.class)
public class RentalRateSessionBean implements RentalRateSessionBeanRemote, RentalRateSessionBeanLocal {

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

    
}
