package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Offer;

import java.util.List;

public interface OfferService {


    public Offer addOffer(Offer offer);
    public List<Offer> getAllOffers();

    public Offer updateOffer(Offer offer);

    public void suppOffer (Long offerId);

}
