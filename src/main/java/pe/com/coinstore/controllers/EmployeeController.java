package pe.com.coinstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.coinstore.entities.Employee;
import pe.com.coinstore.services.EmployeeService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.listAll();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/projects")
    public ResponseEntity<List<Employee>> getAllEmployeesWithProjects() {
        List<Employee> employees = employeeService.listAllWithProjects();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/name/{name}")
    public ResponseEntity<List<Employee>> getEmployeesByName(@PathVariable("name") String name) {
        List<Employee> employees = employeeService.listByName(name);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeesById(@PathVariable("id") Long id) {
        Employee employee = employeeService.listById(id);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }


    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee newEmployee=employeeService.save(employee);
        return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id){
        employeeService.delete(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        Employee foundEmployee=employeeService.listById(id);
        if (employee.getName()!=null) {
            foundEmployee.setName(employee.getName());
        };
        if (employee.getEmail()!=null) {
            foundEmployee.setEmail(employee.getEmail());
        };
        if (employee.getDni()!=null) {
            foundEmployee.setDni(employee.getDni());
        };
        if (employee.getAge()!=0) {
            foundEmployee.setAge(employee.getAge());
        };

        Employee updateEmployee=employeeService.save(foundEmployee);
        return new ResponseEntity<Employee>(updateEmployee, HttpStatus.OK);
    }


    // http://localhost:4200/api/employees/gonzalo

}
