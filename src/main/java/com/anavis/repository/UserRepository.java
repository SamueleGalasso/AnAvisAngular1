package com.anavis.repository;

import com.anavis.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * Interfaccia che ci permette di utilizzare alcuni metodi fondamentali per comunicare col db.
 */
public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
