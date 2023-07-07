package pe.com.coinstore.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.coinstore.entities.Employee;
import pe.com.coinstore.entities.ProjectEmployee;
import pe.com.coinstore.exceptions.IncompleteDataException;
import pe.com.coinstore.exceptions.ResourceNotFoundException;
import pe.com.coinstore.repositories.EmployeeRepository;
import pe.com.coinstore.repositories.ProjectEmployeeRepository;
import pe.com.coinstore.services.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectEmployeeRepository projectEmployeeRepository;

    public List<Employee> listAll(){
        List<Employee> employees;
        employees=employeeRepository.findAll();
        for(Employee e: employees) {
            e.setProjectEmployeeList(null);
        }
        return employees;
    }
    public List<Employee> listByName(String name){
        List<Employee> employees;
        employees=employeeRepository.findByNameContaining(name);
        for(Employee e: employees) {
            e.setProjectEmployeeList(null);
        }
        return employees;
    }

    public Employee listById(Long id) {
        Employee employee;
        employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found an Employee with id="+id) );
        employee.setProjectEmployeeList(null);
        return employee;
    }

    public List<Employee> listAllWithProjects(){
        List<Employee> employees;
        employees=employeeRepository.findAll();
        for(Employee e: employees) {
            for (ProjectEmployee pe: e.getProjectEmployeeList()) {
                pe.setEmployee(null);
                pe.getProject().setProjectEmployeeList(null);
            }
        }
        return employees;
    }

    @Transactional
    public Employee save(Employee employee) {
        if (employee.getName()==null || employee.getName().isEmpty() ) {
            throw new IncompleteDataException("Employee Name can not be null or empty");
        }
        if (employee.getDni()==null || employee.getDni().isEmpty() ) {
            throw new IncompleteDataException("Employee DNI can not be null or empty");
        }

        if (employee.getId()==null || employee.getId()==0) {
            if (employeeRepository.findByDni(employee.getDni()) != null) {
                throw new IncompleteDataException("Value for DNI is duplicated");
            };
        }

        Employee newEmployee = employeeRepository.save(employee);
        return newEmployee;
    };

    @Transactional
    public void delete(Long id, boolean forced) {
        Employee employee = employeeRepository.findById(id).get();
        if (forced) {
            List<ProjectEmployee> projectEmployees = projectEmployeeRepository.findByEmployee_Id(id);
            for(ProjectEmployee pe: projectEmployees) {
                projectEmployeeRepository.delete(pe);
            }
        }
        employeeRepository.delete(employee);
    }

}
