package jp.co.axa.apidemo.web;

import jp.co.axa.apidemo.domain.employee.Employee;
import jp.co.axa.apidemo.domain.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Employee findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public void save(Employee employee) {
        service.save(employee);
        System.out.println("Employee Saved Successfully");
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
        System.out.println("Employee Deleted Successfully");
    }

    @PutMapping
    public void update(@RequestBody Employee employee) {
        if (Objects.nonNull(service.findById(employee.getId()))) {
            service.save(employee);
        }
    }
}
