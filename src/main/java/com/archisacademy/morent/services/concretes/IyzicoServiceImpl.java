package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.IyzicoPaymentRequest;
import com.archisacademy.morent.entities.User;
import com.archisacademy.morent.exceptions.UserNotFoundException;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.services.abstracts.IyzicoService;
import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IyzicoServiceImpl implements IyzicoService {

    private final Options options;
    private final UserRepository userRepository;

    @Override
    public Payment processPayment(IyzicoPaymentRequest request) {

        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        // Ödeme isteği oluşturma
        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();

        createPaymentRequest.setPrice(request.getPrice());
        createPaymentRequest.setPaidPrice(request.getPrice());
        createPaymentRequest.setCurrency(request.getCurrency());
        createPaymentRequest.setInstallment(1);
        createPaymentRequest.setPaymentChannel("WEB");
        createPaymentRequest.setPaymentGroup("PRODUCT");

        // Satın alan kişiyi oluşturma
        Buyer buyer = new Buyer();

        buyer.setId(user.getUserId().toString());
        buyer.setName(user.getFirstName());
        buyer.setSurname(user.getLastName());
        buyer.setGsmNumber(user.getPhoneNumber());
        buyer.setEmail(user.getEmail());
        buyer.setIdentityNumber("11111111111");
        buyer.setRegistrationAddress(user.getAddress());
        buyer.setIp("192.168.1.1");
        buyer.setCity(user.getAddress());
        buyer.setCountry("Turkey");

        // Ödeme isteğinde kullanmak üzere kart oluşturma
        PaymentCard paymentCard = new PaymentCard();

        paymentCard.setCardHolderName(request.getCardHolderName());
        paymentCard.setCardNumber(request.getCardNumber());
        paymentCard.setExpireMonth(request.getExpireMonth());
        paymentCard.setExpireYear(request.getExpireYear());
        paymentCard.setCvc(request.getCvc());

        // Gönderim adresini belirleme
        Address shippingAddress = new Address();

        shippingAddress.setContactName(buyer.getName() + " " + buyer.getSurname());
        shippingAddress.setCity(buyer.getCity());
        shippingAddress.setCountry(buyer.getCountry());
        shippingAddress.setAddress(buyer.getRegistrationAddress());
        shippingAddress.setZipCode("12345");

        // Fatura adresini belirleme
        Address billingAddress = new Address();

        billingAddress.setContactName(buyer.getName() + " " + buyer.getSurname());
        billingAddress.setCity(buyer.getCity());
        billingAddress.setCountry(buyer.getCountry());
        billingAddress.setAddress(buyer.getRegistrationAddress());
        billingAddress.setZipCode("12345");

        // Ürünü kendim girdim entity olmadığı için
        BigDecimal totalBasketPrice = BigDecimal.valueOf(0.0);
        List<BasketItem> basketItems = new ArrayList<>();

        BasketItem basketItem = new BasketItem();
        basketItem.setId("BI001");
        basketItem.setName("X Telefon");
        basketItem.setCategory1("Elektronik");
        basketItem.setItemType(BasketItemType.PHYSICAL.name());
        basketItem.setPrice(BigDecimal.valueOf(150.75));

        basketItems.add(basketItem);
        totalBasketPrice = totalBasketPrice.add(basketItem.getPrice());

        createPaymentRequest.setBasketItems(basketItems);

        // Paymente kullanıcıyı atama
        createPaymentRequest.setBuyer(buyer);

        // Kartı ödeme isteğine atama
        createPaymentRequest.setPaymentCard(paymentCard);

        // Gönderim adresini payment isteğine atama
        createPaymentRequest.setShippingAddress(shippingAddress);

        // Fatura adresini payment isteğine atama
        createPaymentRequest.setBillingAddress(billingAddress);

        return Payment.create(createPaymentRequest, options);
    }

}
