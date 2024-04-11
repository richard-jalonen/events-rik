package ee.richja.backend.api.controller;

import ee.richja.backend.properties.PaymentProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentControllerTest {
    private PaymentProperties paymentProperties;
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        paymentProperties = mock(PaymentProperties.class);
        paymentController = new PaymentController(paymentProperties);
    }

    @Test
    void testGetAllPaymentMethods() {
        List<String> expectedPaymentMethods = Arrays.asList("Cash", "Credit Card", "PayPal", "Bank Transfer");
        when(paymentProperties.getTypes()).thenReturn(expectedPaymentMethods);

        ResponseEntity<List<String>> response = paymentController.getAllPaymentMethods();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPaymentMethods, response.getBody());
    }
}