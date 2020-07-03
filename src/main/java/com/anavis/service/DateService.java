package com.anavis.service;

import com.anavis.domain.Date;

import java.util.List;
import java.util.Optional;

public interface DateService {
    //metodo che ritorna una lista di tutte le date presente nel db
    List<Date> findAll();
    //metodo che cerca e ritorna una specifica data tramite id
    Optional<Date> findOne(Long id);
    //metodo che salva nel db una data e la ritorna
    Date save(Date date);

    //List<Prenotation> blurrySearch(String title);
    //metodo utilizzato per rimuovere una specifica data dal db tramite id
    void removeOne(Long id);
    //factory method
    Date newDate();
    
}
