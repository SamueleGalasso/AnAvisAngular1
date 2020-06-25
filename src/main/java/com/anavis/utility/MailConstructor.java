package com.anavis.utility;

import com.anavis.domain.Prenotation;
import com.anavis.domain.User;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;


@Component
public class MailConstructor {

    @Autowired
    private Environment env;


    public SimpleMailMessage constructNewUserEmail(User user, String password) throws MessagingException {
        String message = "\nPlease use the following credentials to log in and edit your personal information including your own password"+
                "\nUsername: "+user.getUsername()+"\nPassword: "+password;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("AnAvis- New User Credentials");
        email.setText(message);
        email.setFrom(env.getProperty("support.email"));
        return email;

    }

    public SimpleMailMessage bloodDeficiency(User user, String bloodType) throws MessagingException {
        String message = "\nEmergency Blood Deficiency, your blood type is missing in our deposits, you are kindly invited to donate"+
                "\nNome: "+user.getFirstName()+"\nCognome: "+user.getLastName()+"\nGruppo Sanguigno: "+bloodType;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("AnAvis- EMERGENCY, Blood Deficiency!");
        email.setText(message);
        email.setFrom(env.getProperty("support.email"));
        return email;

    }

    public SimpleMailMessage prenotationConfirmed(User user, Prenotation prenotation) throws MessagingException {
        String message = "\nYour prenotation in date: "+prenotation.getDate().getPrenotationDate()+" has been confirmed by an admin";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("AnAvis- Prenotation confirmed!");
        email.setText(message);
        email.setFrom(env.getProperty("support.email"));
        return email;

    }

    public SimpleMailMessage userActivated(User user) throws MessagingException {
        String message = "\nHi "+user.getFirstName()+" "+user.getLastName()+" your account has been activated by an admin, now you are able to prenotate your donation date!";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("AnAvis- Account Activated!");
        email.setText(message);
        email.setFrom(env.getProperty("support.email"));
        return email;

    }

    public SimpleMailMessage userDisabled(User user) throws MessagingException {
        String message = "\nHi "+user.getFirstName()+" "+user.getLastName()+" your account has been disabled by an admin. Contact our admins for more infos.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("AnAvis- Account Disabled!");
        email.setText(message);
        email.setFrom(env.getProperty("support.email"));
        return email;

    }




}
