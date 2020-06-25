package com.anavis.resource;

import com.anavis.config.SecurityConfig;
import com.anavis.config.SecurityUtility;
import com.anavis.domain.User;
import com.anavis.domain.security.Role;
import com.anavis.domain.security.UserRole;
import com.anavis.service.UserService;
import com.anavis.utility.MailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import java.security.Principal;
import java.util.*;

/**
 * Controller che gestisce tutte le richieste inerenti all'user autenticato e loggato (con sessione attiva).
 */
@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    private int bloodCounterA;
    private int bloodCounterB;
    private int bloodCounterAB;
    private int bloodCounter0;

    /**
     * Metodo utilizzato per creare un nuovo utente
     * @param mapper qui viene memorizzato il body della request proveniente dal client,
     * contenente ciò che è stato inserito nel form.
     * @return response entity settando status code a 200 e stampando in console User Added Successfully!
     * @throws Exception
     */
    @RequestMapping(value="/newUser", method=RequestMethod.POST)
    public ResponseEntity newUserPost(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> mapper
    ) throws Exception {
        //qui memorizzo il body della request cioè l'username e l'email inseriti nel form
        String username = mapper.get("username");
        String userEmail = mapper.get("email");
        //nel caso in cui l'username sia gia presente nel db
        if(userService.findByUsername(username) != null) {
            return new ResponseEntity("usernameExists", HttpStatus.BAD_REQUEST);
        }
        //nel caso in cui l'email sia gia presente nel db
        if(userService.findByEmail(userEmail) != null) {
            return new ResponseEntity("emailExists", HttpStatus.BAD_REQUEST);
        }

        //creo il nuovo User e gli setto alcuni campi tra cui la password generata automaticamente in modo random
        User user = userService.newUser();
        user.setUsername(username);
        user.setEmail(userEmail);

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);
        userService.save(user);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);
        user.setEnabled(false);
        //invio all'utente un'email contenente le credenziali tra cui la password generata
        SimpleMailMessage email = mailConstructor.constructNewUserEmail(user, password);
        mailSender.send(email);

        return new ResponseEntity("User Added Successfully!", HttpStatus.OK);

    }

    /**
     * Metodo utilizzato per resettare la password nel caso in cui venga smarrita.
     * @param mapper body della request, da cui estraggo il contenuto del form
     * @return response entity
     * @throws Exception
     */
    @RequestMapping(value="/forgetPassword", method=RequestMethod.POST)
    public ResponseEntity forgetPasswordPost(
            @RequestBody HashMap<String, String> mapper
    ) throws Exception {

        User user = userService.findByEmail(mapper.get("email"));

        //se l'utente non viene trovato tramite l'email inserita nel form ritorno questo
        //bad request
        if(user == null) {
            return new ResponseEntity("Email not found", HttpStatus.BAD_REQUEST);
        }
        String password = SecurityUtility.randomPassword();

        //genero una nuova password la crypto, la salvo nel db al posto della vecchia e mando le nuove credenziali tramite email

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);
        userService.save(user);

        SimpleMailMessage newEmail = mailConstructor.constructNewUserEmail(user, password);
        mailSender.send(newEmail);

        return new ResponseEntity("Email sent!", HttpStatus.OK);

    }

    /**
     * Metodo utilizzato per aggiornare e allo stesso tempo visualizzare le info dell'utente corrente
     * @param mapper contiene il body della richiesta proveniente dal client contenente tutte le credenziali e le info dell'utente.
     * @param principal currentUser
     * @return Response Entity status code 200
     * @throws Exception exception
     */
    @RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
    public ResponseEntity profileInfo(
            @RequestBody HashMap<String, Object> mapper,
            Principal principal
    ) throws Exception{
        //memorizzo tutti i dati dell'utente in queste variabili
        int id = (Integer) mapper.get("id");
        @Email
        String email = (String) mapper.get("email");
        String username = (String) mapper.get("username");
        String firstName = (String) mapper.get("firstName");
        String lastName = (String) mapper.get("lastName");
        String newPassword = (String) mapper.get("newPassword");
        String currentPassword = (String) mapper.get("currentPassword");
        String codiceFiscale = (String) mapper.get("codiceFiscale");
        String paese = (String) mapper.get("paese");
        String citta = (String) mapper.get("citta");
        String stringPhone = (String) mapper.get("phone");
        String birthDate = (String) mapper.get("birthDate");
        String extraUserInfo = (String) mapper.get("extraUserInfo");
        String gruppoSanguigno = (String) mapper.get("gruppoSanguigno");

        //user attualmente loggato e autenticato con sessione attiva
        User currentUser = userService.findByUsername(principal.getName());
        //nel caso non ci sia un utente loggato
        if(currentUser == null) {
            throw new Exception ("User not found");
        }
        //nel caso non venga trovata nel db l'email dell'utente loggato
        if(userService.findByEmail(email) != null) {
            if(userService.findByEmail(email).getId() != currentUser.getId()) {
                return new ResponseEntity("Email not found!", HttpStatus.BAD_REQUEST);
            }
        }
        //nel caso non venga trovato l'username dell'utente loggato
        if(userService.findByUsername(username) != null) {
            if(userService.findByUsername(username).getId() != currentUser.getId()) {
                return new ResponseEntity("Username not found!", HttpStatus.BAD_REQUEST);
            }
        }

        SecurityConfig securityConfig = new SecurityConfig();


        BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
        String dbPassword = currentUser.getPassword();
        //nel caso in cui la password corrente inserita nel form sia errata
        if(null != currentPassword)
            if(passwordEncoder.matches(currentPassword, dbPassword)) {
                if(newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
                    currentUser.setPassword(passwordEncoder.encode(newPassword));
                }
                //currentUser.get().setEmail(email);
            } else {
                return new ResponseEntity("Incorrect current password!", HttpStatus.BAD_REQUEST);
            }
        //nel caso in cui il form della current password venga lasciato vuoto
        if(currentPassword == null){
            return new ResponseEntity("currentPasswordEmpty", HttpStatus.BAD_REQUEST);
        }

        //una serie di if-else utilizzati semplicemente nel caso in cui il form di update venga lasciato vuoto,
        //in quel caso invece di aggiornare il campo con una stringa vuota salvo nuovamente il campo che è gia presente nel db

        //se il form viene lasciato vuoto salvo l'email precedente
        if(email.equals("") || email == null){
            currentUser.setEmail(currentUser.getEmail());
        }//se viene trovata l'email inserita nel form nel db allora vuol dire che è gia in uso e non possiamo cambiarla
        else if(userService.findByEmail(email) != null) {
            return new ResponseEntity("emailExists", HttpStatus.BAD_REQUEST);
        }else{
            currentUser.setEmail(email);
        }

        //se il form viene lasciato vuoto salvo l'username precedente
        if(username.equals("") || username == null){
            currentUser.setUsername(currentUser.getUsername());
        }//se viene trovato l'username inserito nel form nel db già esiste e non possiamo cambiarlo
        else if(userService.findByUsername(username) != null) {
            return new ResponseEntity("usernameExists", HttpStatus.BAD_REQUEST);
        }else{
            currentUser.setUsername(username);
        }
        //se il form viene lasciato vuoto salvo le credenziali precedentemente presenti nel db.

        this.checkUserInfo1(codiceFiscale,stringPhone,firstName,currentUser);
        this.checkUserInfo2(lastName,citta,paese,currentUser);
        this.checkUserInfo3(birthDate, extraUserInfo, gruppoSanguigno, currentUser);

        userService.save(currentUser);

        return new ResponseEntity("Update Success", HttpStatus.OK);
    }

    public void checkUserInfo1(String codiceFiscale, String stringPhone, String firstName, User currentUser){
        //se il form viene lasciato vuoto salvo il codice fiscale precedente
        if(codiceFiscale == null || codiceFiscale == null){
            currentUser.setCodiceFiscale(currentUser.getCodiceFiscale());
        } else {
            currentUser.setCodiceFiscale(codiceFiscale);
        }

        //se il form viene lasciato vuoto salvo il numero di telefono precedente
        if(stringPhone == null || stringPhone == null){
            currentUser.setPhone(currentUser.getPhone());
        } else {
            currentUser.setPhone(stringPhone);
        }

        if(firstName == null || firstName == null){
            currentUser.setFirstName(currentUser.getFirstName());
        }else{
            currentUser.setFirstName(firstName);
        }
    }

    public void checkUserInfo2(String lastName, String citta, String paese, User currentUser){
        if(lastName == null || lastName.equals("")){
            currentUser.setLastName(currentUser.getLastName());
        }else{
            currentUser.setLastName(lastName);
        }

        if(citta == null || citta.equals("")){
            currentUser.setCitta(currentUser.getCitta());
        }else {
            currentUser.setCitta(citta);
        }

        if(paese == null || paese.equals("")) {
            currentUser.setPaese(currentUser.getPaese());
        }else {
            currentUser.setPaese(paese);
        }

    }

    public void checkUserInfo3(String birthDate, String extraUserInfo, String gruppoSanguigno, User currentUser){
        if(birthDate == null || birthDate.equals("")){
            currentUser.setBirthDate(currentUser.getBirthDate());
        }else {
            currentUser.setBirthDate(birthDate);
        }

        if(extraUserInfo == null || extraUserInfo.equals("")){
            currentUser.setExtraUserInfo(currentUser.getExtraUserInfo());
        }else {
            currentUser.setExtraUserInfo(extraUserInfo);
        }

        if(gruppoSanguigno == null || gruppoSanguigno.equals("")){
            currentUser.setGruppoSanguigno(currentUser.getGruppoSanguigno());
        }else {
            currentUser.setGruppoSanguigno(gruppoSanguigno);
        }
    }

    /**
     * Metodo utilizzato per ottenere l'utente corrente autenticato e con sessione valida e attiva.
     * @param principal utente corrente
     * @return l'utente corrente autenticato, loggato e con sessione valida e attiva.
     */
     @RequestMapping("/getCurrentUser")
     //principal è l'user autenticato in questo momento
    public User getCurrentUser(Principal principal){
        User user = userService.newUser();
        //se principal è diverso da null, quindi c'è un utente loggato
        if(null != principal) {
             user = userService.findByUsername(principal.getName());
        }
        //se non entro nell'if ritorno un user vuoto
        return user;
     }

    /**
     * Metodo utilizzato per effettuare il logout
     * @return response entity settando status code a 200 e stampando in console Logout Successfully!
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(){
        SecurityContextHolder.clearContext();
        return new ResponseEntity("Logout Successfully!", HttpStatus.OK);
    }

    /**
     * Metodo utilizzato per ottenere una lista di tutti gli user presenti nel db.
     * @return tutti gli user salvati nel db.
     */
    @RequestMapping("/userList")
    public List<User> userList(){
        return userService.findAll();
    }

    /**
     * Metodo utilizzato per ottenere uno specifico user in base all'id che viene passato come parametro
     * @param id id dell'user che stiamo cercando
     * @return l'user con questo id
     */
    @RequestMapping("{id}")
    public Optional<User> getUser(
            @PathVariable("id") Long id
    ){
        return userService.findById(id);
    }

}