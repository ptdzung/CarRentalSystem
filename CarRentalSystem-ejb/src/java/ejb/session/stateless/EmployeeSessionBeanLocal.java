package ejb.session.stateless;

import java.util.ArrayList;
import entity.EmployeeEntity;
import entity.OutletEntity;
import util.exception.EmployeeNotFoundException;
import util.exception.InvalidLoginCredentialException;
/**
 *
 * @author Trishpal
 */

public interface EmployeeSessionBeanLocal {
    
    public EmployeeEntity login(String username, String password) throws InvalidLoginCredentialException;
    
    public EmployeeEntity retrieveEmployeeByUsername(String username) throws EmployeeNotFoundException;
    
    public Long createNewEmployee(EmployeeEntity employee);
    
}