package com.anavis;

import com.anavis.domain.Date;
import com.anavis.domain.Prenotation;
import com.anavis.domain.User;
import com.anavis.repository.DateRepository;
import com.anavis.repository.PrenotationRepository;
import com.anavis.repository.UserRepository;
import com.anavis.resource.DateResource;
import com.anavis.resource.PrenotationResource;
import com.anavis.service.DateService;
import com.anavis.service.PrenotationService;
import com.anavis.service.UserService;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.helger.commons.mock.CommonsAssert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PrenotationTest {

    @Autowired
    private PrenotationService prenotationService;

    @Autowired
    private PrenotationResource prenotationResource;

    private Prenotation prenotation1;

    private Date date;

    private User user1;

    private User user2;

    private Prenotation prenotation2;

    private Date date2;

    private User user3;

    @Autowired
    private PrenotationRepository prenotationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DateRepository dateRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DateService dateService;

    @Autowired
    private DateResource dateResource;




    @Before
    public void set(){
        user3 = new User();
        user3.setGruppoSanguigno("A");
        user3.setUsername("pippo3");
        userService.save(user3);
        user1 = new User();
        user1.setGruppoSanguigno("A");
        user1.setUsername("pippo1");
        date = new Date();
        prenotation1 = new Prenotation();
        prenotation1.setPrenotationStatus("createdTest1");
        prenotation1.setUser(user1);
        prenotation1.setActive("active");
        user1.setPrenotation(prenotation1);
        userService.save(user1);
        prenotationRepository.save(prenotation1);
        user2 = new User();
        user2.setGruppoSanguigno("A");
        user2.setUsername("pippo2");
        prenotation2 = new Prenotation();
        prenotation2.setPrenotationStatus("createdTest2");
        prenotation2.setUser(user2);
        user2.setPrenotation(prenotation2);
        userService.save(user2);
        prenotationRepository.save(prenotation2);
    }

    @Test
    public void testAddPrenotation(){
        assertEquals(prenotationService.findOne(prenotation1.getId()).get().getId(), prenotation1.getId());
        prenotationService.removeFromDb(prenotation1.getId());
        prenotationService.removeFromDb(prenotation2.getId());
        userService.remove(user1.getId());
        userService.remove(user2.getId());
        userService.remove(user3.getId());
    }

    @Test
    public void testGetPrenotation(){
        assertEquals(prenotationResource.getPrenotation(prenotation1.getId()).get().getId(), prenotation1.getId());
        prenotationService.removeFromDb(prenotation1.getId());
        prenotationService.removeFromDb(prenotation2.getId());
        userService.remove(user1.getId());
        userService.remove(user2.getId());
        userService.remove(user3.getId());
    }

    @Test
    public void removePrenotation(){
        prenotationResource.removeAdmin(this.prenotation1.getId().toString());
        prenotationResource.removeAdmin(this.prenotation2.getId().toString());

        userService.remove(user1.getId());
        userService.remove(user2.getId());
        userService.remove(user3.getId());
        assertEquals(prenotationService.findOne(prenotation1.getId()).isPresent(), false);
    }

    @Test
    public void prenotationDone(){
        prenotationResource.prenotationDone(this.prenotation1);
        assertEquals(this.prenotation1.isDonationDone(), true);
        prenotationService.removeFromDb(this.prenotation1.getId());
        prenotationService.removeFromDb(this.prenotation2.getId());
        prenotationService.removeFromUser(this.prenotation2.getId(), this.user2);
        prenotationService.removeFromUser(this.prenotation1.getId(),this.user1);

        userService.remove(user1.getId());
        userService.remove(user2.getId());
        userService.remove(user3.getId());
    }

    @Test
    public void prenotationList(){
        List<Prenotation> prenotationList = prenotationResource.prenotationList();
        List<Prenotation> filteredList = new ArrayList<>();
        for(Prenotation prenotation: prenotationList){
            if(prenotation.getPrenotationStatus().equals("createdTest1") || prenotation.getPrenotationStatus().equals("createdTest2")){
                filteredList.add(prenotation);
            }
        }
        prenotationService.removeFromDb(this.prenotation1.getId());
        prenotationService.removeFromDb(this.prenotation2.getId());
        prenotationService.removeFromUser(this.prenotation2.getId(), this.user2);
        prenotationService.removeFromUser(this.prenotation1.getId(),this.user1);

        userService.remove(user1.getId());
        userService.remove(user2.getId());
        userService.remove(user3.getId());
        assertEquals(filteredList.size(), 2);
    }

    @Test
    public void checkPrenotation() throws MessagingException {
        prenotationService.removeFromDb(prenotation1.getId());
        prenotationResource.checkPrenotation(this.prenotation1);
        assertEquals(this.prenotation1.getActive(), "inactive");
        prenotationService.removeFromDb(this.prenotation1.getId());
        prenotationService.removeFromDb(this.prenotation2.getId());
        prenotationService.removeFromUser(this.prenotation2.getId(), this.user2);
        prenotationService.removeFromUser(this.prenotation1.getId(),this.user1);

        userService.remove(user1.getId());
        userService.remove(user2.getId());
        userService.remove(user3.getId());
    }

    @Test
    public void prenotationUserList(){
        List<User> userList = prenotationResource.userListByPrenotation();
        List<User> filteredList = new ArrayList<>();

        for(User user : userList){
            if(user.getUsername().equals("pippo1") || user.getUsername().equals("pippo2") || user.getUsername().equals("pippo3")){
                filteredList.add(user);
            }
        }
        prenotationService.removeFromDb(this.prenotation1.getId());
        prenotationService.removeFromDb(this.prenotation2.getId());
        prenotationService.removeFromUser(this.prenotation2.getId(), this.user2);
        prenotationService.removeFromUser(this.prenotation1.getId(),this.user1);

        userService.remove(user1.getId());
        userService.remove(user2.getId());
        userService.remove(user3.getId());
        assertEquals(filteredList.size(), 2);

    }



}
