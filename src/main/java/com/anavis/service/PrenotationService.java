package com.anavis.service;

import com.anavis.domain.Date;
import com.anavis.domain.Prenotation;
import com.anavis.domain.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PrenotationService {
    //crea una prenotazione in base all'utente e alla data che gli vengono passati
    Prenotation createPrenotation(User user, Date date);
    //ritorna la lista delle prenotazioni in base al currentUser (principal)
    List<Prenotation> findAll(Principal principal);
    //cerca nel db e ritorna una particolare prenotazione in base all'id che gli viene passato.
    Optional<Prenotation> findOne(Long id);
    //rimuove dal db una particolare prenotazione tramite id
    void removeFromDb(Long id);
    //factory method
    Prenotation newPrenotation();
}
