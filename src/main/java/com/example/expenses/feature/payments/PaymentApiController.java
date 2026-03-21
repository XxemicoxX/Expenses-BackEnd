package com.example.expenses.feature.payments;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class PaymentApiController {
    private final PaymentService paymentService;

    @GetMapping()
    public ResponseEntity<List<PaymentReaderDTO>> getAll() {
        List<PaymentReaderDTO> payments = paymentService.gtAllPayments();
        if (payments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay payments registrados");
        }
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentReaderDTO> getPayment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(paymentService.gtPaymentById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentReaderDTO> insertPayment(@Valid @RequestBody PaymentWriterDTO payment) {
        try {
            return ResponseEntity.ok(paymentService.addPayment(payment));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentReaderDTO> updatePayment(@Valid @RequestBody PaymentWriterDTO payment) {
        try {
            return ResponseEntity.ok(paymentService.updPayment(payment));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        try {
            paymentService.dltPayment(id);
            return ResponseEntity.ok("payment eliminado");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
