package com.anavis.service;

import com.anavis.domain.User;
import com.anavis.domain.security.UserRole;

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
    //factory method
    User newUser();
}
