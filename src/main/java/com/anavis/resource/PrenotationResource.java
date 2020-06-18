package com.anavis.resource;

import com.anavis.domain.Date;
import com.anavis.domain.Prenotation;
import com.anavis.domain.User;
import com.anavis.service.DateService;
import com.anavis.service.PrenotationService;
import com.anavis.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        prenotationService.removeOne(Long.parseLong(id), principal);
        prenotationService.removeFromDb(Long.parseLong(id));
        return new ResponseEntity("Remove Success", HttpStatus.OK);
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
}
