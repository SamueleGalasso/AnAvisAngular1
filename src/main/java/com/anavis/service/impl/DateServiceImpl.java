package com.anavis.service.impl;

import com.anavis.domain.Date;
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

    public Optional<Date> findOne(Long id) {
        return dateRepository.findById(id);
    }

    public Date save(Date date) {
        return dateRepository.save(date);
    }


    public void removeOne(Long id) {
        dateRepository.deleteById(id);
    }

    @Override
    public Date newDate() {
        return new Date();
    }
    @Override
    public Prenotation setPrenotations(Prenotation prenotation) {
        dateRepository.findById(prenotation.getDate().getId()).get().getPrenotations().add(prenotation);
        return prenotation;
    }
    
}
