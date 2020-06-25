package com.anavis.service.impl;

import com.anavis.domain.User;
import com.anavis.domain.security.UserRole;
import com.anavis.repository.RoleRepository;
import com.anavis.repository.UserRepository;
import com.anavis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Classe contenente tutti i servizi riguardanti l'utente.
 */
@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userRepository.findByUsername(user.getUsername());

        if(localUser != null) {
            LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
        }


        return localUser;
    }

    @Override
    public User save(User user)  {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User newUser() {
        return new User();
    }


    @Override
    public List<User> findAllByPrenotations() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<User> filteredUserList = new ArrayList<>();
        for(User user: userList){
            if(user.getPrenotation() != null){
                    filteredUserList.add(user);
            }else {
                continue;
            }
        }

        return filteredUserList;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByBloodType(String bloodtype) {
        List<User> userList = (List<User>) userRepository.findAll();
        List<User> filteredUserList = new ArrayList<>();
        for(User user: userList){
            if(user.getGruppoSanguigno() != null){
                if(user.getGruppoSanguigno().equals(bloodtype)){
                    filteredUserList.add(user);
                }
            }else {
                continue;
            }

        }
        return filteredUserList;
    }

    @Override
    public User findByPrenotationId(Long id) {
        List<User> userList = (List<User>) userRepository.findAll();
        List<User> filteredUserList = new ArrayList<>();
        for(User user: userList){
            if(user.getPrenotation() != null){
                if(user.getPrenotation().getId().equals(id)){
                    filteredUserList.add(user);
                    break;
                }
            }
            else {
                continue;
            }
        }
        if(filteredUserList.size() == 0){
            return null;
        }
        return filteredUserList.get(0);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
