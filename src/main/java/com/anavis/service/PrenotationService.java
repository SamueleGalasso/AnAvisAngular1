package com.anavis.service;

import com.anavis.domain.Date;
import com.anavis.domain.Prenotation;
import com.anavis.domain.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PrenotationService {
    //salva la prenotazione nel db
    Prenotation save(Prenotation prenotation);
    //crea una prenotazione in base all'utente e alla data che gli vengono passati
    Prenotation createPrenotation(User user, Date date);
    //ritorna la lista delle prenotazioni effettuate dal currentUser (principal)
    List<Prenotation> findAll(Principal principal);
    //cerca nel db e ritorna una particolare prenotazione in base all'id che gli viene passato.
    Optional<Prenotation> findOne(Long id);
    //rimuove dall'utente una particolare prenotazione tramite id
    void removeOne(Long id, Principal principal);
    //rimuove dal db una particolare prenotazione tramite id
    void removeFromDb(Long id);
    //crea l'oggetto Prenotation
    Prenotation newPrenotation();
    //ritorna la lista di tutte le prenotazioni presenti nel db
    List<Prenotation> findAll();
    void removeFromUser(Long id, User user);

}
