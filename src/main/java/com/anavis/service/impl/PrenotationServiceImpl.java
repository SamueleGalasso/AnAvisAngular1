package com.anavis.service.impl;

import com.anavis.domain.Date;
import com.anavis.domain.Prenotation;
import com.anavis.domain.User;
import com.anavis.repository.PrenotationRepository;
import com.anavis.service.PrenotationService;
import com.anavis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe contenente tutti i servizi legati alle prenotazioni
 */
@Service
public class PrenotationServiceImpl implements PrenotationService {


    @Autowired
    private PrenotationRepository prenotationRepository;

    @Autowired
    private UserService userService;

    @Override
    public Prenotation save(Prenotation prenotation) {
        return prenotationRepository.save(prenotation);
    }

    @Override
    public Prenotation createPrenotation(User user, Date date) {
        Prenotation prenotation = this.newPrenotation();
        prenotation.setDate(date);
        prenotation.setUser(user);
        prenotation.setPrenotationStatus("created");
        user.setPrenotation(prenotation);
        prenotation = prenotationRepository.save(prenotation);
        return prenotation;
    }

    @Override
    public List<Prenotation> findAll(Principal principal) {
        List<Prenotation> prenotationList = (List<Prenotation>) prenotationRepository.findAll();
        User user = userService.findByUsername(principal.getName());

        List<Prenotation> activePrenotationList = new ArrayList<>();

        for (Prenotation prenotation : prenotationList) {
            //togli && e ritorna la lista di tutte le prenotazioni
            if(prenotation.getDate().isActive() && prenotation.getUser().getId()==user.getId()) {
                activePrenotationList.add(prenotation);
            }
        }

        return activePrenotationList;
    }

    @Override
    public Optional<Prenotation> findOne(Long id) {
        return prenotationRepository.findById(id);
    }

    @Override
    public void removeOne(Long id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        user.setPrenotation(null);
    }

    @Override
    public void removeFromDb(Long id) {
        prenotationRepository.deleteById(id);
    }

    @Override
    public Prenotation newPrenotation() {
        return new Prenotation();
    }

    @Override
    public List<Prenotation> findAll() {
        List<Prenotation> prenotationList = (List<Prenotation>) prenotationRepository.findAll();
        return prenotationList;
    }



}
