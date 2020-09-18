package com.anavis.service.impl;

import com.anavis.domain.Date;
import com.anavis.domain.Prenotation;
import com.anavis.domain.User;
import com.anavis.repository.PrenotationRepository;
import com.anavis.service.DateService;
import com.anavis.service.PrenotationService;
import com.anavis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

/**
 * Classe contenente tutti i servizi legati alle prenotazioni
 */
@Service
public class PrenotationServiceImpl implements PrenotationService {


    @Autowired
    private PrenotationRepository prenotationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DateService dateService;

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
        System.out.println(user);
        System.out.println(prenotation);
        user.setPrenotation(prenotation);
        //prenotation = prenotationRepository.save(prenotation);
        prenotationRepository.save(prenotation);
        return prenotation;
    }

    @Override
    public List<Prenotation> findAll(Principal principal) {
        List<Prenotation> prenotationList = (List<Prenotation>) prenotationRepository.findAll();
        User user = userService.findByUsername(principal.getName());

        List<Prenotation> activePrenotationList = new ArrayList<>();

        for (Prenotation prenotation : prenotationList) {
            if(prenotation.getUser() == null){
                continue;
            }

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
    public void removeFromUser(Long id, User user){
        User user1 = userService.findById(user.getId()).get();
        user1.setPrenotation(null);
    }

    @Override
    public void removeFromDb(Long id) {
        Prenotation prenotation = prenotationRepository.findById(id).get();
//        Set<Prenotation> filteredPrenotations = new HashSet<>();
//        filteredPrenotations = dateService.findOne(prenotation.getDate().getId()).get().getPrenotations();
          // prenotation.setUser(null);
          // prenotation.setDate(null);
//        for(Prenotation prenotation1 : filteredPrenotations){
//            if(prenotation1.getId() == id){
//                filteredPrenotations.remove(prenotation1);
//                break;
//            }
//        }
           prenotationRepository.delete(prenotation);
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
