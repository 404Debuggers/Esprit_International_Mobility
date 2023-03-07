package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.PaiementStatus;
import com.Debuggers.MobiliteInternational.Entity.Reservation;
import com.Debuggers.MobiliteInternational.Repository.DormRepository;
import com.Debuggers.MobiliteInternational.Repository.ResrvationRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value("${stripe.secret.key}")
    private String secretKey;

    public void chargeCreditCard(int amount, String currency) throws StripeException {
        Stripe.apiKey = secretKey;
        Customer customer = Customer.retrieve("cus_NRtHcuVrgHbkU3");
//4242 4242 4242 4242
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", currency);
        params.put("customer", customer.getId());
        Charge charge = Charge.create(params);
    }

}