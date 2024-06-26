package ee.richja.backend.api.controller;

import ee.richja.backend.properties.PaymentProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentProperties paymentProperties;

    @GetMapping
    public ResponseEntity<List<String>> getAllPaymentMethods() {
        return ResponseEntity.ok(paymentProperties.getTypes());
    }
}
