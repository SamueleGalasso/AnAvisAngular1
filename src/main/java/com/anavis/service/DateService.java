package com.anavis.service;

import com.anavis.domain.Date;
import com.anavis.domain.Prenotation;

import java.util.List;
import java.util.Optional;

public interface DateService {
    //metodo che ritorna una lista di tutte le date presenti nel db
    List<Date> findAll();
    //metodo che cerca tramite id e ritorna una specifica data
    Optional<Date> findOne(Long id);
    //metodo che salva nel db una data e la ritorna
    Date save(Date date);
    //metodo utilizzato per rimuovere una specifica data dal db tramite id
    void removeOne(Long id);
    //metodo utilizzato per aggiungere una prenotazione alla rispettiva data
    void setPrenotations(Prenotation prenotation);
}
