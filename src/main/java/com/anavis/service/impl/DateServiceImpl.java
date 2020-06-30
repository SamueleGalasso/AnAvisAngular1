package com.anavis.service.impl;

import com.anavis.domain.Date;
import com.anavis.domain.Prenotation;
import com.anavis.repository.DateRepository;
import com.anavis.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe contenente tutti i servizi legati alle date.
 */
@Service
public class DateServiceImpl implements DateService {

    @Autowired
    private DateRepository dateRepository;
    @Override
    public List<Date> findAll() {
        List<Date> dateList = (List<Date>) dateRepository.findAll();

        List<Date> activeDateList = new ArrayList<>();

        for (Date date : dateList) {
            if(date.isActive()) {
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
        Date date = dateRepository.findById(id).get();
        Set<Prenotation> prenotationList = new HashSet<>();
        prenotationList = date.getPrenotations();
        for(Prenotation prenotation: prenotationList){
            User user = userService.findByPrenotationId(prenotation.getId());
            prenotationService.removeFromUser(id, user);
            prenotationService.removeFromDb(prenotation.getId());
        }


        dateRepository.deleteById(id);
    }

    @Override
    public void setPrenotations(Prenotation prenotation) {
        dateRepository.findById(prenotation.getDate().getId()).get().getPrenotations().add(prenotation);
    }
}
