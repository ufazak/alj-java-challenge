package jp.co.axa.apidemo.domain.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findById(Long employeeId) {
        return repository.findById(employeeId).orElse(null);
    }

    @Transactional
    public void save(Employee employee) {
        repository.save(employee);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
