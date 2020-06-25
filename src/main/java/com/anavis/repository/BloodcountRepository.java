package com.anavis.repository;

import com.anavis.domain.Bloodcount;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaccia che ci mette a disposizione alcuni metodi fondamentali per comunicare col db.
 */
public interface BloodcountRepository extends CrudRepository<Bloodcount, Long> {
}
