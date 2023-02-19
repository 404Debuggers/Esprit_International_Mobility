package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Repository.OfferRepository;
import com.Debuggers.MobiliteInternational.Services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
        @Autowired
        OfferRepository offerRepository;

        @Override
        public Offer addOffer(Offer offer) {
            return offerRepository.save(offer);
        }

        @Override
        public List<Offer> getAllOffers() {
            List<Offer> offer = (List<Offer>) offerRepository.findAll();
            return offer;
        }

        @Override
        public Offer updateOffer(Offer offer) {
            return offerRepository.save(offer);
        }

        @Override
        public void suppOffer(Long offerId) {
    offerRepository.deleteById(offerId);
        }


    }

