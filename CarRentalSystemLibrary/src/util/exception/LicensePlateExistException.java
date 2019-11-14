/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author Pham The Dzung
 */
public class LicensePlateExistException extends Exception {

    public LicensePlateExistException() {
    }

    public LicensePlateExistException(String message) {
        super(message);
    }
    
}
