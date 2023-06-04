package jp.co.axa.apidemo.web;

import jp.co.axa.apidemo.domain.history.OperationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/history")
public class OperationHistoryController {

    private final OperationHistoryService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ok(service.getAll());
    }
}
