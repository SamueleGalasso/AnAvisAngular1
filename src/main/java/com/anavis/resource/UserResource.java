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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        User user = new User();
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
        //invio all'utente un'email contenente le credenziali tra cui la password generata
        SimpleMailMessage email = mailConstructor.constructNewUserEmail(user, password);
        mailSender.send(email);

        return new ResponseEntity("User Added Successfully!", HttpStatus.OK);

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
        Optional<User> currentUser = userService.findById(Long.valueOf(id));
        //nel caso non ci sia un utente loggato
        if(currentUser == null) {
            throw new Exception ("User not found");
        }
        //nel caso non venga trovata nel db l'email dell'utente loggato
        if(userService.findByEmail(email) != null) {
            if(userService.findByEmail(email).getId() != currentUser.get().getId()) {
                return new ResponseEntity("Email not found!", HttpStatus.BAD_REQUEST);
            }
        }
        //nel caso non venga trovato l'username dell'utente loggato
        if(userService.findByUsername(username) != null) {
            if(userService.findByUsername(username).getId() != currentUser.get().getId()) {
                return new ResponseEntity("Username not found!", HttpStatus.BAD_REQUEST);
            }
        }

        SecurityConfig securityConfig = new SecurityConfig();


        BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
        String dbPassword = currentUser.get().getPassword();
        //nel caso in cui la password corrente inserita nel form sia errata
        if(null != currentPassword)
            if(passwordEncoder.matches(currentPassword, dbPassword)) {
                if(newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
                    currentUser.get().setPassword(passwordEncoder.encode(newPassword));
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
        if(email.equals("")){
            currentUser.get().setEmail(currentUser.get().getEmail());
        }//se viene trovata l'email inserita nel form nel db allora vuol dire che è gia in uso e non possiamo cambiarla
        else if(userService.findByEmail(email) != null) {
            return new ResponseEntity("emailExists", HttpStatus.BAD_REQUEST);
        }else{
            currentUser.get().setEmail(email);
        }

        //se il form viene lasciato vuoto salvo l'username precedente
        if(username.equals("")){
            currentUser.get().setUsername(currentUser.get().getUsername());
        }//se viene trovato l'username inserito nel form nel db già esiste e non possiamo cambiarlo
        else if(userService.findByUsername(username) != null) {
            return new ResponseEntity("usernameExists", HttpStatus.BAD_REQUEST);
        }else{
            currentUser.get().setUsername(username);
        }
        //TODO
        //se il form viene lasciato vuoto salvo il codice fiscale precedente
        if(codiceFiscale == null){
            currentUser.get().setCodiceFiscale(currentUser.get().getCodiceFiscale());
        } else {
            currentUser.get().setCodiceFiscale(codiceFiscale);
        }

        //se il form viene lasciato vuoto salvo il numero di telefono precedente
        if(stringPhone == null){
            currentUser.get().setPhone(currentUser.get().getPhone());
        } else {
            currentUser.get().setPhone(stringPhone);
        }

        if(firstName == null){
            currentUser.get().setFirstName(currentUser.get().getFirstName());
        }else{
            currentUser.get().setFirstName(firstName);
        }

        if(lastName == null){
            currentUser.get().setLastName(currentUser.get().getLastName());
        }else{
            currentUser.get().setLastName(lastName);
        }

        if(citta == null){
            currentUser.get().setCitta(currentUser.get().getCitta());
        }else {
            currentUser.get().setCitta(citta);
        }

        if(paese == null) {
            currentUser.get().setPaese(currentUser.get().getPaese());
        }else {
            currentUser.get().setPaese(paese);
        }

        if(birthDate == null){
            currentUser.get().setBirthDate(currentUser.get().getBirthDate());
        }else {
            currentUser.get().setBirthDate(birthDate);
        }

        if(extraUserInfo.equals("")){
            currentUser.get().setExtraUserInfo(currentUser.get().getExtraUserInfo());
        }else {
            currentUser.get().setExtraUserInfo(extraUserInfo);
        }

        if(gruppoSanguigno.equals("")){
            currentUser.get().setGruppoSanguigno(currentUser.get().getGruppoSanguigno());
        }else {
            currentUser.get().setGruppoSanguigno(gruppoSanguigno);
        }



        userService.save(currentUser.get());

        return new ResponseEntity("Update Success", HttpStatus.OK);
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
     * @return esponse entity settando status code a 200 e stampando in console Logout Successfully!
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(){
        SecurityContextHolder.clearContext();
        return new ResponseEntity("Logout Successfully!", HttpStatus.OK);
    }

}
