<p align="center">
  <img src="https://www.avis.it/wp-content/uploads/2018/08/avis-sos-magazine.jpg" width="50%">
</p>

---

<p align="center">
<img src="https://forthebadge.com/images/badges/made-with-java.svg"/>
  <img src="https://forthebadge.com/images/badges/60-percent-of-the-time-works-every-time.svg"/>
  <img src="https://forthebadge.com/images/badges/built-with-love.svg"/><br></br>
    <b>AnAVIS</b>, progetto realizzato in <b>Angular</b> e <b>Spring</b> per il corso di laurea <b>L-31</b> presso <b>Unicam</b>, <i>nell'anno accademico 2019/2020</i>, realizzato dagli studenti Samuele Galasso, Matteo Carosi e Giovanni Michetti per l'esame di <b>Ingegneria del Software</b>
<a href="https://www.unicam.it/">• Unicam</a>
<a href="https://avis.it">• AVIS</a>
<a href="https://it.wikipedia.org/wiki/Licenza_MIT">• Licenza</a>
</b></p>


Il sistema da sviluppare dovrà consentire ai donatori Avis di prenotare le donazioni on-line e di poter controllare lo storico dei risultati delle proprie prenotazioni e analisi.
Attualmente per poter prenotare una donazione Avis, i donatori devono telefonare o recarsi in una sede la quale riferirà loro quali sono i giorni e gli orari disponibili presso quella sede; ad ogni donazione, prima del prelievo si deve compilare un modello cartaceo in cui si attestano le proprie condizioni di salute.
Il sistema proposto propone un sistema per velocizzare e semplificare questa pratica, nel progetto alcune funzionalità sono state tralasciate concentrandosi principalmente sulla questione delle prenotazione e dell'interazione tra utenti/admin, il sistema è comunque aperto a future estensioni.
L'utente che accede al servizio dovrà prima di tutti iscriversi al sito web, dopodichè dovrà compilare ed aggiornare il proprio profilo, inserendo tutte le informazioni utili per una ipotetica donazione, queste informazioni vengono memorizzate nel database e sarà respnsabilità del donatore tenerle aggiornate per future donazioni. Questo procedimento permette di saltare la compilazione dei vari moduli avis prima di ogni donazione. L'utente può visualizzare le date disponibili pubblicate dagli admin dal loro portale dedicato, se il numero di posti disponibile è sufficiente è possibile effettuare la prenotazione, che deve essere ora valutata e accettata da un admin. L'admin può visualizzare tutte le pending prenotation in tempo reale consultare la "cartella clinica" del donatore e valutare se accettare o meno la prenotazione, una volta confermata l'utente viene notificato. Le prenotazioni possono essere cancellate in qualsiasi momento sia dagli admin che dagli utenti stessi. Quando la donazione viene effettuata, l'addetto avis (admin) deve segnare la prenotazione come completata, facendo così viene resa "inactive" e completata aggiungendo al deposito della sede il tipo di sangue donato. Gli addetti avis (admin) hanno a disposizione altre funzionalità tra cui visualizzare tutti i donatori e le relative informazioni, aggiungere e modificare/eliminare date di prenotazione, visualizzare la quantità di sangue disponibile nei depositi divisi per tipo inoltre è possibile in casi di emergenza inviare una richiesta di "carenza di sangue" (selezionando il tipo) a tutti gli utenti invitandoli a donare e informandoli della situazione di emergenza.

## Tecnologie e struttura del progetto

Il progetto utilizza un'architettura layered costiutita da tre strati:

#### Backend:

- Il framework utilizzato è Spring Boot
- Il carico del lavoro è stato suddiviso usando Controller, Service, Repository ed Entity permettendo cosi di ottimizzare le responsabilità
- Fornisce chiamate API REST per l'interazione con il frontend
- L'autenticazione e l'autorizzazione sono implementati tramite Spring Security e attraverso l'utilizzo di token JWT

#### Frontend:

- Il framework utilizzato è Angular
- Le chiamate al backend vengono intercettate e rese sicure JWT

#### Persistenza

- Per la mappatura degli oggetti Java in Database relazionali viene utilizzato Hibernate
- Il database utilizzato è MySql in localhost

