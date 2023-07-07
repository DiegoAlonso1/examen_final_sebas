package pe.com.coinstore.services;

import pe.com.coinstore.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> listAll();
    public List<Employee> listByName(String name);

    public Employee listById(Long id);

    public List<Employee> listAllWithProjects();
    public Employee save(Employee employee);
    public void delete(Long id, boolean forced);
}
