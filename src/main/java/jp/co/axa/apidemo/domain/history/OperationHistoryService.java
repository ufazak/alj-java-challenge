package jp.co.axa.apidemo.domain.history;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.axa.apidemo.domain.history.OperationHistory.OperationType;
import jp.co.axa.apidemo.infra.security.AuthHolderService;
import jp.co.axa.apidemo.infra.security.auth.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationHistoryService {

    private final OperationHistoryRepository repository;
    private final AuthHolderService<UserAuth> authHolderService;
    private final ObjectMapper objectMapper;

    @Transactional
    public void addEntry(OperationType operationType, Object entry) {
        try {
            UserAuth userAuth = authHolderService.auth();
            repository.save(new OperationHistory(
                    userAuth.getUser().getId(),
                    operationType,
                    objectMapper.writeValueAsString(entry)
            ));
        } catch (Exception ex) {
            System.out.println(operationType + " operation failed");
        }
    }

    @Transactional(readOnly = true)
    public List<OperationHistory> getAll() {
        return repository.findAll();
    }
}
