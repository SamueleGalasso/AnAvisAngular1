package com.anavis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Questa classe rappresenta le prenotazioni effettuate dagli utenti.
 */
@Entity
@Table(name = "user_prenotation")
public class Prenotation implements Serializable {

    private static final long serialVersionUID = 4123142445L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Date date;

    private String active = "inactive";

    private boolean donationDone = false;

    public boolean isDonationDone() {
        return donationDone;
    }

    public void setDonationDone(boolean donationDone) {
        this.donationDone = donationDone;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @OneToOne
    @JsonIgnore
    private User user;

    private String prenotationStatus;

    public String getPrenotationStatus() {
        return prenotationStatus;
    }

    public void setPrenotationStatus(String prenotationStatus) {
        this.prenotationStatus = prenotationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }









}
