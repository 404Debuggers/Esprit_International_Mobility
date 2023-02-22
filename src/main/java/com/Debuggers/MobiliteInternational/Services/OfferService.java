package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Enum.StudyField;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.UserOfferFav;

import java.util.List;
import java.util.Set;

public interface OfferService {


    public Offer addOffer(Offer offer);
    public List<Offer> getAllOffers();

    public Offer updateOffer(Offer offer);

    public void suppOffer (Long offerId);

    void sendEmail(List<String> to, String subject, String body);

    public void addFavandAssigntouser(Long user_Id , Long offerId);

    public List<Offer> getFavOffers(Long user_Id);

    public List<StudyField> extractPropertiesFromOffers(Long user_Id);

    public List<Offer> findOffersWithSimilarProperties(Long user_Id);

    //public List<Offer> findSimilarOffersForUser(Long userId);


}
