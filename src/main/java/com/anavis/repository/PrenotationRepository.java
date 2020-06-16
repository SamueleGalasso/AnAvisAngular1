package com.anavis.repository;

import com.anavis.domain.Prenotation;
import org.springframework.data.repository.CrudRepository;
/**
 * Interfaccia che ci permette di utilizzare alcuni metodi fondamentali per comunicare col db.
 */
public interface PrenotationRepository extends CrudRepository<Prenotation, Long> {
}
