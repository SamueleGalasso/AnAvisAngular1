package com.anavis.service;

import com.anavis.domain.User;
import com.anavis.domain.security.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    //metodo usato per creare un user.
    User createUser(User user, Set<UserRole> userRoles);
    //metodo usato per cercare un user nel db tramite username
    User findByUsername(String username);
    //metodo usato per cercare un user nel db tramite email
    User findByEmail(String email);
    //metodo per salvare un user nel db
    User save(User user);
    //metodo per cercare un user nel db tramite id
    Optional<User> findById(Long id);
    //metodo usato per rimuovere un utente dal db tramite id
    void remove(Long id);
    //metodo usato per creare l'oggetto User
    User newUser();
    /**
     * Ritorna la lista degli utenti che hanno almeno una prenotazione.
     * @return lista degli utenti con almeno una prenotazione
     */
    List<User> findAllByPrenotations();

    /**
     *
     * @return la lista di tutti gli utenti presenti nel db
     */
    List<User> findAll();

    /**
     *
     * @param bloodtype
     * @return una lista di utenti con lo stesso gruppo sanguigno indicato nel parametro
     */
    List<User> findAllByBloodType(String bloodtype);

    /**
     * Metodo usato per cercare un User tramite l'id della sua prenotazione
     * @param id id della prenotazione
     * @return ritorna l'user che ha come prenotazione, una prenotazione con questo id.
     */
    User findByPrenotationId(Long id);
    

}
