package com.anavis.service;

import com.anavis.domain.Bloodcount;

public interface BloodcountService {
    /**
     * Metodo utilizzato per ottenere l'oggetto Bloodcount utilizzato per contare la quantità di sangue che entra nei depositi
     * @return oggetto bloodcount
     */
    Bloodcount getBloodcount();

    /**
     * Metodo utilizzato per salvare nel db il bloodcount
     * @param bloodcount
     * @return bloodcount salvato nel db.
     */
    Bloodcount save(Bloodcount bloodcount);
}
