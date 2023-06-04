package jp.co.axa.apidemo.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {
}
