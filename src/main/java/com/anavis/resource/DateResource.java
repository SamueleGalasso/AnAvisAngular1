package com.anavis.resource;

import com.anavis.domain.Date;
import com.anavis.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Controller per tutto ciò che riguarda le date, le richieste inviate dal client sono gestite direttamente qui, in base all'url
 * viene eseguita una determinata funzione.
 */
@RestController
@RequestMapping("/date")
public class DateResource {

    @Autowired
    private DateService dateService;


    /**
     * Metodo utilizzato da parte di un admin per aggiungere una nuova data.
     * @param date data da aggiungere
     * @return l'entità salvata nel db
     */
    @RequestMapping (value="/add", method=RequestMethod.POST)
    public Date addDatePost(
            @RequestBody Date date
    ) {
        return dateService.save(date);
    }

    /**
     * Metodo utilizzato per visualizzare la lista di tutte le date disponibili nel db.
     * @return ritorna la lista di date da visualizzare
     */
    @RequestMapping("/dateList")
    public List<Date> getDateList() {
        return dateService.findAll();
    }

    /**
     * Metodo utilizzato da un utente per aggiornare i campi di una data in caso si vogliano aggiornare questi ultimi
     * @param date la data da aggiornare
     * @return l'entità salvata nel db.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Date updateDatePost(@RequestBody Date date) {
        return dateService.save(date);
    }

    /**
     * Metodo utilizzato da un utente per visualizzare una specifica data.
     * @param id id della data da visualizzare
     * @return la data da visualizzare
     */
    @RequestMapping("{id}")
    public Optional<Date> getDate(@PathVariable("id") Long id){
        Optional<Date> prenotation = dateService.findOne(id);
        return prenotation;
    }

    /**
     * Metodo utilizzato per rimuovere una data attualmente presente nel db.
     * @param id id della data da rimuovere dal db.
     * @return ritorna una response entity in caso di successo settando HttpStatus a 200 e stampando in console Remove Success
     * @throws IOException exception
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity remove(
            @RequestBody String id
    )throws IOException {
        dateService.removeOne(Long.parseLong(id));
        return new ResponseEntity("Remove Success", HttpStatus.OK);
    }

}
