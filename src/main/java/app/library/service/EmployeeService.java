package app.library.service;

import app.library.entity.Employee;
import app.library.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save an Employee
    public String save(Employee employee) {
        employeeRepository.save(employee);
        return "Employee: " + employee.getName() + ", successfully saved";
    }

    // Save more Employees
    public List<Employee> saveAll(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    // Update an Employee by ID
    public String update(Employee employee, Long id) {
        employee.setId(id);
        employeeRepository.save(employee);
        return employee.getName() + " successfully updated!";
    }

    // Delete an Employee by ID
    public String delete(Long id) {
        employeeRepository.deleteById(id);
        return "Employee with id: " + id + " deleted";
    }

    // List all Employees
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    // Find an Employee by ID
    public Employee findById(Long id) {
        return employeeRepository.findById(id).get();
    }

    // Verify employee existence by ID
    public boolean existsById(Long id) {
        return employeeRepository.existsById(id);
    }
}