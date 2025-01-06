package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.PaymentRequest;
import com.archisacademy.morent.dtos.responses.PaymentResponse;
import com.archisacademy.morent.entities.Booking;
import com.archisacademy.morent.entities.Payment;
import com.archisacademy.morent.exceptions.book.BookingNotFoundException;
import com.archisacademy.morent.repositories.BookingRepository;
import com.archisacademy.morent.repositories.PaymentRepository;
import com.archisacademy.morent.services.abstracts.PaymentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public PaymentResponse initiatePayment(PaymentRequest paymentRequest) {


        Optional<Booking> byBookingIdEquals = bookingRepository.findByBookingIdEquals(paymentRequest.getBookingId());
        if (byBookingIdEquals.isPresent()) {
            Payment payment = new Payment();
            payment.setPaymentId(paymentRequest.getBookingId());
            payment.setAmount(paymentRequest.getAmount());
            payment.setBooking(byBookingIdEquals.get());
            payment.setPaymentMethod(paymentRequest.getPaymentMethod());
            Payment save = paymentRepository.save(payment);
            PaymentResponse paymentResponse = modelMapper.map(save, PaymentResponse.class);
            paymentResponse.setMessage("Payment initiated");
            return paymentResponse;
        }else {
            throw new BookingNotFoundException("Booking not found with this id: " + paymentRequest.getBookingId());
        }
    }
}