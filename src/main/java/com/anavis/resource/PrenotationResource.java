package com.anavis.resource;

import com.anavis.domain.Bloodcount;
import com.anavis.domain.Date;
import com.anavis.domain.Prenotation;
import com.anavis.domain.User;
import com.anavis.service.BloodcountService;
import com.anavis.service.DateService;
import com.anavis.service.PrenotationService;
import com.anavis.service.UserService;
import com.anavis.utility.MailConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Controller che gestisce tutte le request provenienti dal client inerenti alle prenotazioni.
 */
@RestController
@RequestMapping("/prenotation")
public class PrenotationResource {

    private Prenotation prenotation = new Prenotation();

    @Autowired
    private DateService dateService;

    @Autowired
    private UserService userService;

    @Autowired
    private PrenotationService prenotationService;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BloodcountService bloodcountService;


    /**
     * Metodo utilizzato da un utente autenticato per effettuare una prenotazione
     * @param mapper qui è presente il body della request proveniente dal client
     * @param principal utente autenticato e attivo al momento (currentUser).
     * @return la prenotazione effettuata.
     */
    @RequestMapping("/add")
    public Prenotation addPrenotation(
            @RequestBody HashMap<String, Object> mapper,
            Principal principal
    ){
        ObjectMapper om = new ObjectMapper();
        Date date   = om.convertValue(mapper.get("date"), Date.class);
        User user = userService.findByUsername(principal.getName());
        dateService.findOne(date.getId()).get().setRemainingNumber(dateService.findOne(date.getId()).get().getRemainingNumber() - 1);

        Prenotation prenotation = prenotationService.createPrenotation(user, date);
        dateService.setPrenotations(prenotation);

        user.setPrenotation(prenotation);

        this.prenotation = prenotation;

        return prenotation;


    }

    /**
     * Metodo utilizzato per visualizzare una specifica prenotazione
     * @param id id della prenotazione
     * @return ritorna la prenotazione che si vuole visualizzare
     */
    @RequestMapping("{id}")
    public Optional<Prenotation> getPrenotation(@PathVariable("id") Long id){
        Optional<Prenotation> prenotation = prenotationService.findOne(id);
        return prenotation;
    }

     /**
     * Metodo utilizzato da un utente autenticato per rimuovere una prenotazione effettuata precedentemente
     * @param id id della prenotazione
     * @param principal utente attualmente autenticato e loggato (currentUser)
     * @return response entity settando status code a 200 e stampando in console Remove Success
     * @throws IOException
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity remove(
            @RequestBody String id,
            Principal principal
    )throws IOException {
        //se la prenotazione salvata nell'user è diversa da quella che vogliamo eliminare
        if(userService.findByUsername(principal.getName()).getPrenotation().getId() != Long.parseLong(id)){
            prenotationService.findOne(Long.parseLong(id)).get().getDate().setRemainingNumber(
                    prenotationService.findOne(Long.parseLong(id)).get().getDate().getRemainingNumber() + 1);
            prenotationService.removeFromDb(Long.parseLong(id));
            return new ResponseEntity("Remove Success", HttpStatus.OK);
        }else {
            dateService.findOne(userService.findByPrenotationId(Long.parseLong(id)).getPrenotation().getDate().getId()).get()
                    .setRemainingNumber(userService.findByPrenotationId(Long.parseLong(id))
                            .getPrenotation().getDate().getRemainingNumber() + 1);
            prenotationService.removeOne(Long.parseLong(id), principal);
            prenotationService.removeFromDb(Long.parseLong(id));
            return new ResponseEntity("Remove Success", HttpStatus.OK);
        }
    }
/**
     * Serve per rimuovere una prenotazione, nel caso in cui un'addetto voglia eliminare una prenotazione.
     * @param id id della prenotazione
     * @return response entity status code 200, ok!
     */
    @RequestMapping(value = "/admin/remove", method = RequestMethod.POST)
    public ResponseEntity removeAdmin(
            @RequestBody String id
    ){
        //se la prenotazione da rimuovere non è quella corrente di nessun user
        if(userService.findByPrenotationId(Long.parseLong(id)) == null){
            prenotationService.removeFromDb(Long.parseLong(id));
            dateService.findOne(userService.findByPrenotationId(Long.parseLong(id)).getPrenotation().getDate().getId()).get()
                    .setRemainingNumber(userService.findByPrenotationId(Long.parseLong(id))
                            .getPrenotation().getDate().getRemainingNumber() + 1);
            return new ResponseEntity("Remove Success Admin",HttpStatus.OK);
            //se la prenotazione ha la data nulla (implementato per i test)
        }else if(prenotationService.findOne(Long.parseLong(id)).get().getDate() == null){
            prenotationService.removeFromDb(Long.parseLong(id));
            userService.findByPrenotationId(Long.parseLong(id)).setPrenotation(null);
            return new ResponseEntity("Remove Success Admin", HttpStatus.OK);
        } else{
            dateService.findOne(userService.findByPrenotationId(Long.parseLong(id)).getPrenotation().getDate().getId()).get()
                    .setRemainingNumber(userService.findByPrenotationId(Long.parseLong(id))
                            .getPrenotation().getDate().getRemainingNumber() + 1);
        userService.findByPrenotationId(Long.parseLong(id)).setPrenotation(null);
        prenotationService.removeFromDb(Long.parseLong(id));
        return new ResponseEntity("Remove Success Admin", HttpStatus.OK);
        }
    }
    /**
     * Metodo utilizzato per prendere dal db l'oggetto bloodcount
     * @return l'oggetto bloodcount
     */
    @RequestMapping("/bloodCount")
    public Bloodcount getBloodcount(){
        return bloodcountService.getBloodcount();
    }

   /**
     * Metodo utilizzato per indicare che una prenotazione è terminata, cioè la donazione è stata effettuata.
     * @param prenotation la prenotazione effettuata
     * @return response entity 200 ok!
     */
    @RequestMapping("/prenotationDone")
    public ResponseEntity prenotationDone(
            @RequestBody Prenotation prenotation
    ){
        User user = userService.findByPrenotationId(prenotation.getId());
        if(user == null){
            return new ResponseEntity("No User Found!",HttpStatus.BAD_REQUEST);
        }
        prenotation.setUser(user);
        if(prenotation.getUser().getGruppoSanguigno() == null){
            return new ResponseEntity("No Blood Type!", HttpStatus.BAD_REQUEST);
        }
        prenotation.setActive("inactive");
        prenotation.setDonationDone(true);
        prenotation.setUser(user);
        prenotationService.save(prenotation);
        Bloodcount bloodcount = bloodcountService.getBloodcount();

        if(prenotation.getUser().getGruppoSanguigno().equals("A")){
            bloodcount.setTypeA((bloodcount.getTypeA() + 1));
        }
        if(prenotation.getUser().getGruppoSanguigno().equals("B")){
            bloodcount.setTypeB(bloodcount.getTypeB() + 1);
        }
        if(prenotation.getUser().getGruppoSanguigno().equals("AB")){
            bloodcount.setTypeAB(bloodcount.getTypeAB() + 1);
        }
        if(prenotation.getUser().getGruppoSanguigno().equals("0")){
            bloodcount.setType0(bloodcount.getType0() + 1);
        }
        bloodcountService.save(bloodcount);



        return new ResponseEntity("Prenotation Done!",HttpStatus.OK);
    }



    /**
     * Metodo utilizzato per visualizzare la lista delle preotazioni effettuate dall'utente nel corso del tempo.
     * @param principal l'utente che è attualmente loggato e attivo (currentUser)
     * @return ritorna la lista di tutte le prenotazioni effettuate dall'utente che ne fa richiesta.
     */
    @RequestMapping("/getPrenotationList")
    public List<Prenotation> getPrenotationList(Principal principal) {
        return prenotationService.findAll(principal);
    }

    /**
     * Metodo utilizzato per visualizzare la lista di tutte le prenotazioni effettuate dagli utenti
     * @return la lista di tutte le prenotazioni presenti nel db
     */
    @RequestMapping("/prenotationList")
    public List<Prenotation> prenotationList(){
        return prenotationService.findAll();
    }

    /**
     * Metodo utilizzato per attivare una prenotazione inizialmente inattiva, nel caso in cui un addetto avis
     * ritenga necessaria o adeguata la prenotazione dell'utente dopo una valutazione della cartella clinica del donatore
     * spunta la prenotazione settandola ad active.
     * @return response entity status code 200, ok!
     */
    @RequestMapping("/checkPrenotation")
    public ResponseEntity checkPrenotation(
            @RequestBody Prenotation prenotation
    ) throws MessagingException {
        User user = userService.findByPrenotationId(prenotation.getId());
        if(user == null){
            return new ResponseEntity("No User Found!",HttpStatus.BAD_REQUEST);
        }
        if(prenotation.getActive().equals("inactive")) {
            prenotation.setActive("active");
            SimpleMailMessage email = mailConstructor.prenotationConfirmed(user,prenotation);
            mailSender.send(email);
        }
        else if(prenotation.getActive().equals("active")) {
            prenotation.setActive("inactive");
        }
        prenotation.setUser(user);

        prenotationService.save(prenotation);

        return new ResponseEntity("Status Switched Successfully", HttpStatus.OK);
    }

    /**
     * Metodo usato per ritornare una lista di utenti che hanno fatto almeno una prenotazione.
     * @return lista di utenti che hanno fatto almeno una prenotazione
     */
    @RequestMapping("/prenotationUserList")
    public List<User> userListByPrenotation(){
        return userService.findAllByPrenotations();
    }
    
    /**
     * Metodo utilizzato per ottenere un utente tramite l'id della propria prenotazione
     * @param id id della prenotazione
     * @return utente che ha come prenotazione una prenotazione con questo id
     */
    @RequestMapping("/getUserByPrenotation/{id}")
    public User getUserByPrenotation(
            @PathVariable("id") String id
    ) {
        if(userService.findByPrenotationId(Long.parseLong(id)) == null){
            User user = new User();
            user.setCitta("fake");
            return user;
        }else {
            return userService.findByPrenotationId(Long.parseLong(id));
        }

    }

}
