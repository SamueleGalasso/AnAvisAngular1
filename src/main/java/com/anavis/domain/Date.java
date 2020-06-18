package com.anavis.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Questa classe rappresenta una particolare data in cui è possibile effettuare una prenotazione da parte di un utente autenticato.
 */

@Entity
public class Date implements Serializable {
    private static final long serialVersionUID = 425345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String place;
    private String prenotationDate;

    private boolean active = true;

    @Column(columnDefinition = "text")
    private String description;

    private int remainingNumber;
    
    @OneToMany(mappedBy = "date", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Prenotation> prenotations = new HashSet<>();

    public Set<Prenotation> getPrenotations() {
        return prenotations;
    }

    public void setPrenotations(Set<Prenotation> prenotations) {
        this.prenotations = prenotations;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



    public String getPrenotationDate() {
        return prenotationDate;
    }

    public void setPrenotationDate(String publicationDate) {
        this.prenotationDate = publicationDate;
    }



    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRemainingNumber() {
        return remainingNumber;
    }

    public void setRemainingNumber(int remainingNumber) {
        this.remainingNumber = remainingNumber;
    }

}
