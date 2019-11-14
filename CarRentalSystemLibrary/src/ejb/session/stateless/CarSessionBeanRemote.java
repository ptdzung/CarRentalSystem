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
import java.util.List;
import util.enumerator.StatusEnum;
import util.exception.CarCategoryNotFoundException;
import util.exception.CarModelNotFoundException;
import util.exception.CarNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.LicensePlateExistException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
public interface CarSessionBeanRemote {

    void createNewCar(String licensePlate, String make, String modelName, StatusEnum status, OutletEntity outlet) throws InputDataValidationException, UnknownPersistenceException, LicensePlateExistException;

    Long createNewCarModel(CarModelEntity carModel) throws InputDataValidationException, UnknownPersistenceException;

    List<CarEntity> retrieveAllCars();

    Long createNewCar(CarEntity car) throws InputDataValidationException, UnknownPersistenceException, LicensePlateExistException;

    List<CarModelEntity> retrieveAllCarModels();

    CarEntity retrieveCarById(Long carId) throws CarNotFoundException;

    void updateCar(CarEntity car) throws CarNotFoundException, InputDataValidationException;

    boolean deleteCar(Long carId) throws CarNotFoundException;

    boolean deleteCarModel(Long carModelId) throws CarModelNotFoundException;

    void updateCarModel(CarModelEntity carModel) throws CarModelNotFoundException, InputDataValidationException;

    CarEntity retrieveCarByCarModel(String make, String modelName) throws CarNotFoundException, CarModelNotFoundException;

    CarCategoryEntity retrieveCarCategoryByName(String categoryName) throws CarCategoryNotFoundException;

    CarModelEntity retrieveCarModelById(Long carModelId) throws CarModelNotFoundException;

    CarModelEntity retrieveCarModel(String make, String modelName) throws CarModelNotFoundException;

    OutletEntity retrieveOutletById(Long outletId);

    CarEntity retrieveCarByLicensePlate(String licensePlate) throws CarNotFoundException;
    
    
}
