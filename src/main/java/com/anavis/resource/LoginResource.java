package com.anavis.resource;

import com.anavis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoginResource {


    @Autowired
    private UserService userService;

    /**
     * Metodo utilizzato per verificare la sessione dell'utente appena creata e tenerne traccia.
     * Se questo endpoint viene raggiunto l'utente è gia autenticato, del come se ne occupa SpringSecurity.
     */
    @RequestMapping("/token")
    public Map<String, String> token(HttpSession session, HttpServletRequest request) {


        System.out.println(request.getRemoteHost());

        String remoteHost = request.getRemoteHost();
        int portNumber = request.getRemotePort();

        System.out.println(remoteHost+":"+portNumber);
        System.out.println(request.getRemoteAddr());
        System.out.println(Collections.singletonMap("token", session.getId()));

        return Collections.singletonMap("token", session.getId());
    }

    /**
     *Metodo Utilizzato per verificare e tenere traccia della sessione dell'utente admin che effettua il login nel portale di
     * accesso degli admin.
     * Se viene raggiunto questo endpoint l'admin è gia autenticato, del come viene autenticato se ne occupa SpringSecurity
     */
    @RequestMapping("/token/admin")
    public Map<String, String> tokenAdmin(HttpSession session, HttpServletRequest request) {


        System.out.println(request.getRemoteHost());

        String remoteHost = request.getRemoteHost();
        int portNumber = request.getRemotePort();

        System.out.println(remoteHost+":"+portNumber);
        System.out.println(request.getRemoteAddr());
        System.out.println(Collections.singletonMap("token", session.getId()));

        return Collections.singletonMap("token", session.getId());
    }

    /**
     * Metodo utilizzato per controllare la sessione attiva
     * @return response entity settando status code a 200 e stampando in console Session Active!
     */
    @RequestMapping("/checkSession")
    public ResponseEntity checkSession() {
        return new ResponseEntity("Session Active!", HttpStatus.OK);
    }


}
