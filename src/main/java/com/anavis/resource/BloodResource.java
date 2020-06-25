package com.anavis.resource;

import com.anavis.domain.User;
import com.anavis.service.UserService;
import com.anavis.utility.MailConstructor;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blood")
public class BloodResource {

    @Autowired
    private UserService userService;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private JavaMailSender mailSender;


    /**
     * Metodo utilizzato per inviare una mail a tutti i donatori attivi dello specifico gruppo sanguigno per informarli
     * della carenza di sangue invitandoli a donare.
     * @param bloodType tipo richiesto
     * @return response entity 200 ok!
     * @throws MessagingException
     */
    @RequestMapping("/deficiency")
    private ResponseEntity bloodDeficiencyRequest(
            @RequestBody String bloodType
    ) throws MessagingException {
        String blood = bloodType.substring(1, bloodType.length()-1);
        List<User> userList = userService.findAllByBloodType(blood);
        System.out.println(userList.toString());
        for(User user : userList){
            SimpleMailMessage email = mailConstructor.bloodDeficiency(user, blood);
            mailSender.send(email);
        }


           return new ResponseEntity("Requests Sent Successfully!", HttpStatus.OK);
    }





}
