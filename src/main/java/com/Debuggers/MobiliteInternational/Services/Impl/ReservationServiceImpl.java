package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.PaiementStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Reservation;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.DormRepository;
import com.Debuggers.MobiliteInternational.Repository.ResrvationRepository;
import com.Debuggers.MobiliteInternational.Security.Services.IMailService;
import com.Debuggers.MobiliteInternational.Services.ReservationService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.param.ChargeCreateParams;

import com.stripe.model.Token;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    ResrvationRepository reservationRepository;
    CandidacyRepository candidacyRepository;
    JavaMailSender javaMailSender;
    DormRepository dormRepository;


    IMailService mailService;
    @Override
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(long id) {

        return reservationRepository.findById(id).orElse(null);

    }

    @Override
    public Reservation addReservation(Reservation r, Long candidacyId, Long dormId) {
        Candidacy c = candidacyRepository.findById(candidacyId).orElse(null);
        Dormitories dorm = dormRepository.findById(dormId).orElse(null);
        if(c.getStatus()==Status.ACCEPTED)
        {
            if(dorm.getDormstatus() == DormStatus.Available && dorm.getNbPlaces()!=0)
            {
                r.setDorm(dorm);
                r.setCandidacy(c);
                r.setReservationDate(new Date());
                r.setPaiementStatus(PaiementStatus.PENDING);
                r.setArchive(true);


                dorm.getReservationSet().add(r);

                dorm.setNbPlaces(dorm.getNbPlaces() -1);

                if(dorm.getNbPlaces()==0){
                    dorm.setDormstatus(DormStatus.valueOf("Not_Available"));
                }


                return  reservationRepository.save(r);
            }
        }
        return null;
    }

    @Override
    public List<Dormitories> getReservationAvailable() {
        List<Dormitories> dormitories = dormRepository.findAll();
        List<Dormitories> availableDormitories = new ArrayList<>();

        for (Dormitories dormitory : dormitories) {
            if (dormitory.getDormstatus() == DormStatus.Available) {
                availableDormitories.add(dormitory);
            }
        }

        return availableDormitories;
    }




    @Override
    public Reservation UpdateReservation(Long reservationId, Long newDormitoriesId) {

        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            return null;
        }

        Dormitories newDormitory = dormRepository.findById(newDormitoriesId).orElse(null);
        if (newDormitory == null) {
            return null;
        }
        if (newDormitory.getDormstatus() != DormStatus.Available || newDormitory.getNbPlaces() == 0) {
            return null;
        }
        reservation.getDorm().setNbPlaces(reservation.getDorm().getNbPlaces()+1);
        if (reservation.getDorm().getNbPlaces() != 0) {
            reservation.getDorm().setDormstatus(DormStatus.valueOf("Available"));
        }
        reservation.setDorm(newDormitory);
        newDormitory.getReservationSet().add(reservation);
        newDormitory.setNbPlaces(newDormitory.getNbPlaces() - 1);
        if (newDormitory.getNbPlaces() == 0) {
            newDormitory.setDormstatus(DormStatus.Not_Available);

        }

        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(long id) {

     Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            Dormitories dorm = reservation.getDorm();
            dorm.setNbPlaces(dorm.getNbPlaces() + 1);

            dorm.setDormstatus(DormStatus.valueOf("Available"));
            reservation.setArchive(false);
            reservationRepository.save(reservation);
        }

    }

    public void payDormFees(String cardNumber, String expMonth, String expYear, String cvc, int amount, String currency, Long reservationId, String recipientEmail) throws StripeException, DocumentException, MessagingException, IOException {
        Stripe.apiKey = "sk_test_51MgwMqJCxsodhoZagXsEAADx8Au3VX5m0C1DfVse2VtGL1xQqXWfaD8QXrSPR7QwAnC4AGHATk1z5FW92N3KYADl00nxUCWZ7W";
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            Dormitories dorm = reservation.getDorm();
            if (dorm != null) {
                // Create a new Stripe customer with the card details
                Map<String, Object> customerParams = new HashMap<>();
                customerParams.put("email", reservation.getCandidacy().getUser().getEmail());
                customerParams.put("source", createToken(cardNumber, expMonth, expYear, cvc));
                Customer customer = Customer.create(customerParams);

                // Charge the customer's card for the amount
                Map<String, Object> chargeParams = new HashMap<>();
                chargeParams.put("amount", amount);
                chargeParams.put("currency", currency);
                chargeParams.put("customer", customer.getId());
                Charge charge = Charge.create(chargeParams);
                charge.getAmount();
                // Update the reservation payment status to "succeeded"
                reservation.setPaiementStatus(PaiementStatus.SUCCEEDED);
                reservationRepository.save(reservation);

                // Update the dormitory availability and number of places
                dorm.setNbPlaces(dorm.getNbPlaces() - 1);
                if (dorm.getNbPlaces() == 0) {
                    dorm.setDormstatus(DormStatus.Not_Available);
                }
                dormRepository.save(dorm);
            }
            String subject = "New offer available";
            String body =  "Dear user,\n\n" +
                    "Payement avec succées";

            sendEmail(recipientEmail, subject, body);
        }
    }

    private void sendEmail(String recipientEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    private String createToken(String cardNumber, String expMonth, String expYear, String cvc) throws StripeException {
        Map<String, Object> tokenParams = new HashMap<>();
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", expMonth);
        cardParams.put("exp_year", expYear);
        cardParams.put("cvc", cvc);
        tokenParams.put("card", cardParams);
        Token token = Token.create(tokenParams);
        return token.getId();
    }

    @Scheduled(cron ="0 * * * * ?" ) // Run at midnight every day
    public void updateArchivedOffers() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        Date sevenDaysAgo = calendar.getTime();

        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            if (reservation.getReservationDate() != null &&
                    reservation.getReservationDate().before(sevenDaysAgo) &&
                    reservation.getPaiementStatus().equals(PaiementStatus.PENDING) &&
                    reservation.getArchive() == true) {
                reservation.setArchive(false);
                reservationRepository.save(reservation);
            }
        }
    }


}