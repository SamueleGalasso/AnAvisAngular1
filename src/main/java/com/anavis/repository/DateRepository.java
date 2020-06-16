package com.anavis.repository;

import com.anavis.domain.Date;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaccia che ci permette di utilizzare alcuni metodi fondamentali per comunicare col db.
 */
public interface DateRepository extends CrudRepository<Date, Long>{
       //List<Prenotation> findByTitleContaining(String keyword);

}
