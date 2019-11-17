/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CarCategoryEntity;
import entity.CarEntity;
import entity.CarModelEntity;
import entity.OutletEntity;
import entity.RentalRateEntity;
import entity.RentalRecordEntity;
import entity.TravelDispatchRecordEntity;
import java.util.ArrayList;
import java.util.Date;
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
import util.exception.CarNotFoundException;
import util.exception.CarModelNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.LicensePlateExistException;
import util.exception.RentalRateNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
@Stateless
@Local(CarSessionBeanLocal.class)
@Remote(CarSessionBeanRemote.class)
public class CarSessionBean implements CarSessionBeanRemote, CarSessionBeanLocal {

    @EJB
    private RentalRateSessionBeanLocal rentalRateSessionBeanLocal;

    @EJB
    private DispatchSessionBeanLocal dispatchSessionBeanLocal;
    
    

    @PersistenceContext(unitName = "CarRentalManagementSystem-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public CarSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    
    @Override
    public void createNewCar(String licensePlate, String make, String modelName, StatusEnum status, OutletEntity outlet) {
        Query query = em.createQuery("SELECT cm FROM CarModelEntity cm WHERE cm.make = :inMake AND cm.modelName = :inModelName");
        query.setParameter("inMake", make);
        query.setParameter("inModelName", modelName);
        CarModelEntity carModel = (CarModelEntity)query.getSingleResult();
        
        try {
            createNewCar(new CarEntity(licensePlate, carModel, status, outlet));
        } catch (InputDataValidationException | UnknownPersistenceException | LicensePlateExistException ex) {
            ex.getMessage();
        }
    }
    
    
    @Override
    public Long createNewCar(CarEntity car) throws InputDataValidationException, UnknownPersistenceException, LicensePlateExistException {
        Set<ConstraintViolation<CarEntity>>constraintViolations = validator.validate(car);
        
        if(constraintViolations.isEmpty())
        {  
            try
            {
                em.persist(car);
                em.flush();

                return car.getCarId();
            }
            catch(PersistenceException ex)
            {
                if(ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException"))
                {
                    if(ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException"))
                    {
                        throw new LicensePlateExistException();
                    }
                    else
                    {
                        throw new UnknownPersistenceException(ex.getMessage());
                    }
                }
                else
                {
                    throw new UnknownPersistenceException(ex.getMessage());
                }
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsForCar(constraintViolations));
        }
    }

    @Override
    public Long createNewCarModel(CarModelEntity carModel) throws InputDataValidationException, UnknownPersistenceException {
        try
        {
            Set<ConstraintViolation<CarModelEntity>>constraintViolations = validator.validate(carModel);
        
            if(constraintViolations.isEmpty())
            {
                em.persist(carModel);
                em.flush();

                return carModel.getCarModelId();
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsForCarModel(constraintViolations));
            }            
        }
        catch(PersistenceException ex) {
                throw new UnknownPersistenceException(ex.getMessage());
        }
    }

    @Override
    public List<CarEntity> retrieveAllCars() {
        Query query = em.createQuery("SELECT c FROM CarEntity c ORDER BY c.carModel.carCategory ASC");
        
        return query.getResultList();
    }

    @Override
    public List<CarModelEntity> retrieveAllCarModels() {
        Query query = em.createQuery("SELECT cm FROM CarModelEntity cm ORDER BY cm.carCategory ASC");
        
        return query.getResultList();
    }

    @Override
    public CarEntity retrieveCarById(Long carId) throws CarNotFoundException {
        CarEntity car = em.find(CarEntity.class, carId);
        
        if(car != null) {
            return car;
        } else {
            throw new CarNotFoundException("Car ID " + carId + " does not exist!");
        } 
    }
    
    

    @Override
    public void updateCar(CarEntity car) throws CarNotFoundException, InputDataValidationException {
        if(car != null && car.getCarId()!= null)
        {
            Set<ConstraintViolation<CarEntity>>constraintViolations = validator.validate(car);
        
            if(constraintViolations.isEmpty())
            {
                CarEntity carToUpdate = retrieveCarById(car.getCarId());
                
                carToUpdate.setLicensePlate(car.getLicensePlate());
                carToUpdate.setStatus(car.getStatus());
                carToUpdate.setOutlet(car.getOutlet());
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsForCar(constraintViolations));
            }
        }
        else
        {
            throw new CarNotFoundException("Car ID is not provided for car to be updated");
        }
    }
    
    private String prepareInputDataValidationErrorsForCar(Set<ConstraintViolation<CarEntity>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }

    @Override
    public boolean deleteCar(Long carId) throws CarNotFoundException{
        CarEntity car = retrieveCarById(carId);
        CarModelEntity carModel = car.getCarModel();
        
        if(car.getRentalRecord() == null) {
            carModel.removeCar(car);
            em.remove(car);
            return true;
        } else {
            car.setStatus(StatusEnum.DISABLED);
            return false;
        }
    }

    @Override
    public boolean deleteCarModel(Long carModelId) throws CarModelNotFoundException {
        CarModelEntity carModel = em.find(CarModelEntity.class, carModelId);
        if (carModel != null) {
            CarCategoryEntity carCategory = carModel.getCarCategory();
            if (carModel.getRentalRecords().isEmpty()) {
                carCategory.removeCarModel(carModel);
                em.remove(carModel);
                return true;
            } else {
                carModel.setStatus(StatusEnum.DISABLED);
                return false;
            }
        } else {
            throw new CarModelNotFoundException("Car Model ID " + carModelId + " does not exist!");
        }
    }

    @Override
    public void updateCarModel(CarModelEntity carModel) throws CarModelNotFoundException, InputDataValidationException {
        if(carModel != null && carModel.getCarModelId() != null) {
            Set<ConstraintViolation<CarModelEntity>>constraintViolations = validator.validate(carModel);
            
            if(constraintViolations.isEmpty()) {
                CarModelEntity carModelToUpdate = em.find(CarModelEntity.class, carModel.getCarModelId());
                
                carModelToUpdate.setMake(carModel.getMake());
                carModelToUpdate.setModelName(carModel.getModelName());
                carModelToUpdate.setStatus(carModel.getStatus());
            } else {
                throw new InputDataValidationException(prepareInputDataValidationErrorsForCarModel(constraintViolations));
            }
        } else {
            throw new CarModelNotFoundException("Car Model ID is not provided for car to be updated");
        }
    }
    
    private String prepareInputDataValidationErrorsForCarModel(Set<ConstraintViolation<CarModelEntity>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }

    @Override
    public CarEntity retrieveCarByCarModel(String make, String modelName) throws CarNotFoundException, CarModelNotFoundException {
        CarModelEntity carModel = retrieveCarModel(make, modelName);
        if (carModel != null) {
            Query query = em.createQuery("SELECT c FROM CarEntity c WHERE c.carModel = :inModel");
            query.setParameter("inModel", carModel);
            try {
                return (CarEntity)query.getSingleResult();
            } catch (NoResultException | NonUniqueResultException ex) {
                throw new CarNotFoundException("Car does not exist!");
            }
        } else {
            throw new CarModelNotFoundException("Car model " + make + " " + modelName + " does not exist!");
        }
    }

    @Override
    public CarCategoryEntity retrieveCarCategoryByName(String categoryName) throws CarCategoryNotFoundException {
        Query query = em.createQuery("SELECT cc FROM CarCategoryEntity cc WHERE cc.categoryName = :inCategoryName");
        query.setParameter("inCategoryName", categoryName);
        
        try {
            CarCategoryEntity carCategory = (CarCategoryEntity)query.getSingleResult();
            return carCategory;
        } catch(NoResultException | NonUniqueResultException ex) {
            throw new CarCategoryNotFoundException("Car category " + categoryName + " does not exist!");
        }
    }

    @Override
    public CarModelEntity retrieveCarModelById(Long carModelId) throws CarModelNotFoundException {
        CarModelEntity cm = em.find(CarModelEntity.class, carModelId);
        
        if(cm != null) {
            return cm;
        } else {
            throw new CarModelNotFoundException("Car Model ID " + carModelId + " does not exist!");
        } 
    }

    @Override
    public CarModelEntity retrieveCarModel(String make, String modelName) throws CarModelNotFoundException {
        Query query = em.createQuery("SELECT cm FROM CarModelEntity cm WHERE cm.make = :inMake AND cm.modelName = :inModelName");
        query.setParameter("inMake", make);
        query.setParameter("inModelName", modelName);
        try {
            return (CarModelEntity)query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new CarModelNotFoundException("Car model " + make + " " + modelName + " does not exist!");
        }
    }

    @Override
    public OutletEntity retrieveOutletById(Long outletId) {
        return em.find(OutletEntity.class, outletId);
    }

    @Override
    public CarEntity retrieveCarByLicensePlate(String licensePlate) throws CarNotFoundException, InputDataValidationException {
        if (licensePlate.length() == 8) {
            Query query = em.createQuery("SELECT c FROM CarEntity c WHERE c.licensePlate = :inPlate");
            query.setParameter("inPlate", licensePlate);
            try {
                return (CarEntity)query.getSingleResult();
            } catch (NoResultException | NonUniqueResultException ex) {
                throw new CarNotFoundException("License plate " + licensePlate + " does not exist");
            }
        } else {
            throw new InputDataValidationException("License plate input is invalid!");
        }
    }

    @Override
    public CarEntity retrieveCarForAllocation(RentalRecordEntity record) {
        CarCategoryEntity category = record.getCarCategory();
        OutletEntity pickupOutlet = record.getPickupOutlet();
        Date pickupDate = record.getRentedFrom();
        
        Query query = em.createQuery("SELECT c FROM CarEntity c WHERE c.carModel.carCategory = :inCategory");
        query.setParameter("inCategory", category);
        List<CarEntity> cars = query.getResultList();
        if (cars.isEmpty()) return null;
        
        //Car is at outlet already
        for (CarEntity c : cars) {
            if (c.getStatus().equals(StatusEnum.AVAILABLE) && c.getOutlet().equals(pickupOutlet)) {
                return c;
            }
        }
        //Car is currently rented, but will be return to outlet before pickup time
        for (CarEntity c : cars) {
            if (c.getStatus().equals(StatusEnum.RENTED) && c.getRentalRecord().getReturnOutlet().equals(pickupOutlet)) {
                if (pickupDate.getTime() - c.getRentedTo().getTime() >= 0) return c;
            }
        }
        //Car is currently at another outlet -> need transit
        for (CarEntity c : cars) {
            if (c.getStatus().equals(StatusEnum.AVAILABLE) && !c.getOutlet().equals(pickupOutlet)) {
                try {
                    dispatchSessionBeanLocal.createNewTravelDispatchRecord(new TravelDispatchRecordEntity(pickupOutlet, record));
                } catch (InputDataValidationException | UnknownPersistenceException ex) {
                    ex.getMessage();
                }
                return c;
            }
        }
        //Car is currently rented and going to be returned to another outlet -> need transit
        for (CarEntity c : cars) {
            if (c.getStatus().equals(StatusEnum.RENTED)) {
                if (pickupDate.getTime() - c.getRentedTo().getTime() >= 7200000) {
                    try {
                        dispatchSessionBeanLocal.createNewTravelDispatchRecord(new TravelDispatchRecordEntity(pickupOutlet, record));
                    } catch (InputDataValidationException | UnknownPersistenceException ex) {
                        ex.getMessage();
                    }
                    return c;
                }
            }
        }
        
        return null;
        
        
//        if (record.getCarModel() == null) {
//            List<CarModelEntity> models = category.getCarModels();
//            for (CarModelEntity cm : models) {
//                List<CarEntity> cars = cm.getCars();
//                for (CarEntity c : cars) {
//                    if (c.getOutlet().equals(pickupOutlet)) {
//                        return c;
//                    } else if (c.getRentedTo() == null) {
//                        return c;
//                    } else if (c.getRentalRecord().getReturnOutlet().equals(pickupOutlet)) {
//                        if (c.getRentedTo().compareTo(pickupDate) <= 0) {
//                            return c;
//                        }
//                    } else if (c.getOutlet() == null && c.getRentedTo().compareTo(record.getRentedFrom()) <= 7200) {
//                        
//                    }
//                }
//            }
//        }
    }

    @Override
    public List<CarEntity> searchCar(CarCategoryEntity category, CarModelEntity model, Date startDate, Date endDate, OutletEntity pickupOutlet) {
        List<CarEntity> returnList = new ArrayList<>();
        
        if (model == null) {
            Query query = em.createQuery("SELECT c FROM CarEntity c WHERE c.carModel.carCategory = :inCategory");
            query.setParameter("inCategory", category);
            List<CarEntity> cars = query.getResultList();
            
            for (CarEntity c : cars) {
                if (c.getStatus().equals(StatusEnum.AVAILABLE) && c.getOutlet().equals(pickupOutlet)) {
                    returnList.add(c);
                } else if (c.getStatus().equals(StatusEnum.RENTED) && c.getRentalRecord().getReturnOutlet().equals(pickupOutlet)) {
                    if (startDate.getTime() - c.getRentedTo().getTime() >= 0l) {
                        returnList.add(c);
                    }
                } else if (c.getStatus().equals(StatusEnum.AVAILABLE) && !c.getOutlet().equals(pickupOutlet)) {
                    returnList.add(c);
                } else if (c.getStatus().equals(StatusEnum.RENTED)) {
                    if (startDate.getTime() - c.getRentedTo().getTime() >= 7200000l) {
                        returnList.add(c);
                    }
                }
            }
            return returnList;
        } else {
            Query query = em.createQuery("SELECT c FROM CarEntity c WHERE c.carModel = :inModel");
            query.setParameter("inModel", model);
            List<CarEntity> cars = query.getResultList();
            
            for (CarEntity c : cars) {
                if (c.getStatus().equals(StatusEnum.AVAILABLE) && c.getOutlet().equals(pickupOutlet)) {
                    returnList.add(c);
                } else if (c.getStatus().equals(StatusEnum.RENTED) && c.getRentalRecord().getReturnOutlet().equals(pickupOutlet)) {
                    if (startDate.getTime() - c.getRentedTo().getTime() >= 0l) {
                        returnList.add(c);
                    }
                } else if (c.getStatus().equals(StatusEnum.AVAILABLE) && !c.getOutlet().equals(pickupOutlet)) {
                    returnList.add(c);
                } else if (c.getStatus().equals(StatusEnum.RENTED)) {
                    if (startDate.getTime() - c.getRentedTo().getTime() >= 7200000l) {
                        returnList.add(c);
                    }
                }
            }
            return returnList;
        }
    }
    
    
}
