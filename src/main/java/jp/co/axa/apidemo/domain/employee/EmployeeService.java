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
    public Employee findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Transactional
    public void delete(Employee employee) {
        repository.delete(employee);
    }
}
