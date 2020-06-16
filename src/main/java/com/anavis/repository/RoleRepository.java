package com.anavis.repository;

import org.springframework.data.repository.CrudRepository;

import com.anavis.domain.security.Role;
/**
 * Interfaccia che ci permette di utilizzare alcuni metodi fondamentali per comunicare col db.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

}
