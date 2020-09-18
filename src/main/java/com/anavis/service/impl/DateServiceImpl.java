package com.anavis.service.impl;

import com.anavis.domain.Date;
import com.anavis.domain.Prenotation;
import com.anavis.domain.User;
import com.anavis.repository.DateRepository;
import com.anavis.service.DateService;
import com.anavis.service.PrenotationService;
import com.anavis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Classe contenente tutti i servizi legati alle date.
 */
@Service
public class DateServiceImpl implements DateService {

    @Autowired
    private DateRepository dateRepository;

    @Autowired
    private PrenotationService prenotationService;

    @Autowired
    private UserService userService;

    @Override
    public List<Date> findAll() {
        List<Date> dateList = (List<Date>) dateRepository.findAll();

        List<Date> activeDateList = new ArrayList<>();

        for (Date date : dateList) {
            if (date.isActive()) {
                activeDateList.add(date);
            }
        }

        return activeDateList;
    }

    @Override
    public Optional<Date> findOne(Long id) {
        return dateRepository.findById(id);
    }

    @Override
    public Date save(Date date) {
        return dateRepository.save(date);
    }

    @Override
    public void removeOne(Long id) {
        Date date = dateRepository.findById(id).orElse(null);
        Set<Prenotation> prenotationList;
        if (date.getPrenotations() != null) {
            prenotationList = date.getPrenotations();
            for (Prenotation prenotation : prenotationList) {
                User user = userService.findByPrenotationId(prenotation.getId());
                if(user == null){
                    prenotationService.removeFromDb(prenotation.getId());
                    continue;
                }else{
                    prenotationService.removeFromUser(id, user);
                }
                //prenotationService.removeFromDb(prenotation.getId());
                for (Prenotation prenotation_ : prenotationList) {
                    prenotationService.removeFromDb(prenotation_.getId());
                }
            }
        }

        dateRepository.deleteById(id);

    }






    @Override
    public void setPrenotations(Prenotation prenotation) {
        dateRepository.findById(prenotation.getDate().getId()).get().getPrenotations().add(prenotation);
    }
}
