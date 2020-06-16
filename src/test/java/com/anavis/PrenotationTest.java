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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        user1 = new User();
        date = new Date();
        date.setPlace("SBT");
        date.setPrenotationDate("2020-12-11");
        date.setActive(true);
        date.setDescription("Test Description");
        date.setRemainingNumber(12);
        dateResource.addPrenotationPost(date);
        prenotation1 = new Prenotation();
        prenotation1.setPrenotationStatus("created");
        prenotation1.setDate(date);
        prenotation1.setUser(user1);
        user1.setPrenotation(prenotation1);
        userService.save(user1);
        prenotationRepository.save(prenotation1);
    }

    @Test
    public void testAddPrenotation(){
        assertEquals(prenotationService.findOne(prenotation1.getId()).get().getId(), prenotation1.getId());
        prenotationService.removeFromDb(prenotation1.getId());
        userService.remove(user1.getId());
        dateService.removeOne(date.getId());


    }

    @Test
    public void testGetPrenotation(){
        assertEquals(prenotationResource.getPrenotation(prenotation1.getId()).get().getId(), prenotation1.getId());
        prenotationService.removeFromDb(prenotation1.getId());
        userService.remove(user1.getId());
        dateService.removeOne(date.getId());
    }

//    @Test
//    public void testGetPrenotationList(){
//        List<Prenotation> prenotationList = prenotationResource.getPrenotationList(principal);
//        assertEquals(prenotationList.get(0).getId(), prenotation1.getId());
//        assertEquals(prenotationList.get(1).getId(), prenotation2.getId());
//        dateService.removeOne(date.getId());
//        userService.remove(user1.getId());
//        userService.remove(user2.getId());
//        prenotationService.removeFromDb(prenotation1.getId());
//        prenotationService.removeFromDb(prenotation2.getId());
//    }

//    @Test
//    public void testRemovePrenotation() throws IOException {
//
//        prenotationResource.remove(prenotation1.getId().toString(), principal);
//        assertEquals(prenotationResource.getPrenotationList(principal).size(), 1);
//        dateService.removeOne(date.getId());
//        userService.remove(user1.getId());
//        userService.remove(user2.getId());
//        prenotationService.removeFromDb(prenotation2.getId());
//    }


}
