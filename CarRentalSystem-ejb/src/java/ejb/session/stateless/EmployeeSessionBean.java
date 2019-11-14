package ejb.session.stateless;

import entity.EmployeeEntity;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.EmployeeNotFoundException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Trishpal
 */
@Stateless
@Local(EmployeeSessionBeanLocal.class)
@Remote(EmployeeSessionBeanRemote.class)
public class EmployeeSessionBean implements EmployeeSessionBeanRemote, EmployeeSessionBeanLocal {

    @PersistenceContext(unitName = "CarRentalManagementSystem-ejbPU")
    private EntityManager em;

    @Override
    public EmployeeEntity login(String username, String password) throws InvalidLoginCredentialException{
        try{
            EmployeeEntity emp = retrieveEmployeeByUsername(username);
            if(emp.getPassword().equals(password)){
                emp.getEmployeeId();
                emp.getAccessRightEnum();
                return emp;
            }else {
                throw new InvalidLoginCredentialException("Invalid Login Credentials");
            }
            
        }catch(EmployeeNotFoundException ex){
            throw new InvalidLoginCredentialException("Invalid Login Credentials");
        }
    }
    
    @Override
    public EmployeeEntity retrieveEmployeeByUsername(String username) throws EmployeeNotFoundException{
        Query query = em.createQuery("SELECT e FROM EmployeeEntity e WHERE e.username = :username");
        query.setParameter("username", username);
        
        try {
            return (EmployeeEntity) query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex){
            throw new EmployeeNotFoundException("Invalid Login Credentials");
        }
    }
    
    @Override
    public Long createNewEmployee(EmployeeEntity employee){
        em.persist(employee);
        em.flush();
        
        return employee.getEmployeeId();
    }
    
    
}