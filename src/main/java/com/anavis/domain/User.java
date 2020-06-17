package com.anavis.domain;

import com.anavis.domain.security.Authority;
import com.anavis.domain.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Questa classe rappresenta gli utenti autenticati. Infatti al momento della registrazione viene creato e salvato nel db.
 */
@Entity
public class User implements UserDetails, Serializable{

    private static final long serialVersionUID = 902783495L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id", nullable=false, updatable = false)
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String codiceFiscale;
    private String gruppoSanguigno;
    private String paese;
    private String citta;

    @OneToMany(cascade = CascadeType.ALL)
    private Prenotation prenotation;

    public Prenotation getPrenotation() {
        return prenotation;
    }

    public void setPrenotation(Prenotation prenotation) {
        this.prenotation = prenotation;
    }

    @Column(columnDefinition="text")
    private String extraUserInfo;


    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getExtraUserInfo() {
        return extraUserInfo;
    }

    public void setExtraUserInfo(String extraUserInfo) {
        this.extraUserInfo = extraUserInfo;
    }

    public String getGruppoSanguigno() {
        return gruppoSanguigno;
    }

    public void setGruppoSanguigno(String gruppoSanguigno) {
        this.gruppoSanguigno = gruppoSanguigno;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    private String email;
    private String phone;
    private boolean enabled = true;




    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }




}
