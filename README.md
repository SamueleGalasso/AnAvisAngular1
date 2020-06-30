<p align="center">
  <img src="https://www.avis.it/wp-content/uploads/2018/08/avis-sos-magazine.jpg" width="50%">
</p>

---

<p align="center">
<img src="https://forthebadge.com/images/badges/made-with-java.svg"/>
  <img src="https://forthebadge.com/images/badges/60-percent-of-the-time-works-every-time.svg"/>
  <img src="https://forthebadge.com/images/badges/built-with-love.svg"/><br></br>
    <b>AnAVIS</b>, progetto realizzato in <b>Angular</b> e <b>Spring</b> per il corso di laurea <b>L-31</b> presso <b>Unicam</b>, <i>nell'anno accademico 2019/2020</i>, realizzato dagli studenti <b>Samuele Galasso</b>, <b>Giovanni Michetti</b> e <b>Matteo Carosi</b> per l'esame di <b>Ingegneria del Software</b>
<a href="https://www.unicam.it/">• Unicam</a>
<a href="https://avis.it">• AVIS</a>
<a href="https://it.wikipedia.org/wiki/Licenza_MIT">• Licenza</a>
</b></p>


Il sistema da sviluppare dovrà consentire ai donatori Avis di prenotare le donazioni on-line e di poter controllare lo storico dei risultati delle proprie prenotazioni e analisi.
Attualmente per poter prenotare una donazione Avis, i donatori devono telefonare o recarsi in una sede la quale riferirà loro quali sono i giorni e gli orari disponibili presso quella sede; ad ogni donazione, prima del prelievo si deve compilare un modello cartaceo in cui si attestano le proprie condizioni di salute.
Il sistema proposto propone un sistema per velocizzare e semplificare questa pratica, nel progetto alcune funzionalità sono state tralasciate concentrandosi principalmente sulla questione delle prenotazione e dell'interazione tra utenti/admin, il sistema è comunque aperto a future estensioni.
L'utente che accede al servizio dovrà prima di tutti iscriversi al sito web, dopodichè dovrà compilare ed aggiornare il proprio profilo, inserendo tutte le informazioni utili per una ipotetica donazione, queste informazioni vengono memorizzate nel database e sarà responsabilità del donatore tenerle aggiornate per future donazioni. Questo procedimento permette di saltare la compilazione dei vari moduli avis prima di ogni donazione facendo risparmiare molto tempo. L'utente può visualizzare le date disponibili pubblicate dagli admin dal loro portale dedicato, se il numero di posti disponibile è sufficiente è possibile effettuare la prenotazione, che deve essere ora valutata e accettata da un admin. L'admin può visualizzare tutte le pending prenotation in tempo reale consultare la "cartella clinica" del donatore e valutare se accettare o meno la prenotazione, una volta confermata l'utente viene notificato. Le prenotazioni possono essere cancellate in qualsiasi momento sia dagli admin che dagli utenti stessi. Quando la donazione viene effettuata, l'addetto avis (admin) deve segnare la prenotazione come completata, facendo così viene resa "inactive" e completata aggiungendo al deposito della sede il tipo di sangue donato. Gli addetti avis (admin) hanno a disposizione altre funzionalità tra cui visualizzare tutti i donatori e le relative informazioni, aggiungere e modificare/eliminare date di prenotazione, visualizzare la quantità di sangue disponibile nei depositi divisi per tipo inoltre è possibile in casi di emergenza inviare una richiesta di "carenza di sangue" (selezionando il tipo) a tutti gli utenti invitandoli a donare e informandoli della situazione di emergenza.

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

## Iterazioni


**Prima iterazione (dal 12/05/20 al 30/05/20):**
- Incontro 1 -> Identificazione attori e parte dei casi d'uso
- Incontro 2 -> Flow of events di alcuni casi d'uso
- Incontro 3 -> Creazione class diagram e alcuni sequence diagram
- Incontro 4 -> Revisione del diagramma dei casi d'uso
- Incontro 5 -> Revisione del class diagram e dei sequence diagram
- Incontro 6 -> Implementata prima configurazione del sistema

**Seconda iterazione (dal 1/06/20 al 17/06/20):**
- Incontro 1 -> Lavoro ai casi d'uso
- Incontro 2 -> Lavoro al class diagram e ai sequence diagram
- Incontro 3 -> Revisione di alcuni diagrammi
- Incontro 4 -> Implementazione test casi d'uso principali
- Incontro 5 -> Implementazione casi d'uso principali
- Incontro 6 -> Correzione bug backend
- Incontro 7 -> Correzione bug frontend

**Terza iterazione (dal 18/06/20 al 3/07/20):**
- Incontro 1 -> Revisione dei sequence diagram
- Incontro 2 -> Revisione dei sequence diagram e lavoro ai casi d'uso
- Incontro 3 -> Lavoro su ulteriori sequence diagram
- Incontro 4 -> Revisione di alcuni diagrammi
- Incontro 5 -> Implementazione di ulteriori test
- Incontro 6 -> Implementazione di tutti i components del frontend
- Incontro 7 -> Implementazione backend
- Incontro 8 -> Revisione backend

## Responsabilità
-Frontend: Interamente realizzato da Samuele Galasso;
-Backend: realizzato da Giovanni Michetti, Matteo Carosi, Samuele Galasso.


