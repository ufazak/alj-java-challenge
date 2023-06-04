package jp.co.axa.apidemo.web;

import jp.co.axa.apidemo.domain.employee.Employee;
import jp.co.axa.apidemo.domain.employee.EmployeeService;
import jp.co.axa.apidemo.domain.history.OperationHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static jp.co.axa.apidemo.domain.history.OperationHistory.OperationType.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final OperationHistoryService operationHistoryService;

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Employee findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Employee requestBody) {
        Employee employee = service.save(requestBody);
        operationHistoryService.addEntry(CREATE, employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        Employee employee = service.findById(id);
        if (Objects.nonNull(employee)) {
            service.delete(employee);
            operationHistoryService.addEntry(DELETE, employee);
        }
    }

    @PutMapping
    public void update(@RequestBody Employee requestBody) {
        if (Objects.nonNull(service.findById(requestBody.getId()))) {
            Employee employee = service.save(requestBody);
            operationHistoryService.addEntry(UPDATE, employee);
        }
    }
}
