package com.example.expenses.feature.payments;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public List<PaymentReaderDTO> gtAllPayments() {
        return paymentRepository.findAll().stream().map(paymentMapper::toDto).toList();
    }

    public PaymentReaderDTO gtPaymentById(Long id) {
        return paymentMapper.toDto(paymentRepository.findById(id).orElseThrow());
    }

    public PaymentReaderDTO addPayment(PaymentWriterDTO payment) {
        return save(payment);
    }

    public PaymentReaderDTO updPayment(PaymentWriterDTO payment) throws Exception {
        if (!paymentRepository.existsById(payment.id())) {
            throw new Exception("ID no encontrado");
        }
        return save(payment);
    }

    public String dltPayment(Long id) throws Exception {
        if (!paymentRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        paymentRepository.deleteById(id);
        return String.format("Payment eliminado con el ID: %d", id);
    }

    private PaymentReaderDTO save(PaymentWriterDTO payment) {
        return paymentMapper.toDto(paymentRepository.save(paymentMapper.toEntity(payment)));
    }
}
