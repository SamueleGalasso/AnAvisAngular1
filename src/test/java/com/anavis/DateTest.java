package com.anavis;

import com.anavis.domain.Date;
import com.anavis.resource.DateResource;
import com.anavis.service.DateService;
import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

import static com.helger.commons.mock.CommonsAssert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DateTest {


    private Date date,date2;

    @Autowired
    private DateService dateService;

    @Autowired
    private DateResource dateResource;

    @Before
    public void set(){
        date = new Date();
        date2 = new Date();
        date.setPlace("SBT");
        date.setPrenotationDate("2020-12-11");
        date.setActive(true);
        date.setDescription("Test1");
        date.setRemainingNumber(12);
        date2.setPlace("PDA");
        date2.setPrenotationDate("2020-10-09");
        date2.setActive(true);
        date2.setDescription("Test2");
        date2.setRemainingNumber(10);
        dateResource.addDatePost(date);
        dateResource.addDatePost(date2);

        Hibernate.initialize(date.getPrenotations());
        Hibernate.initialize(date2.getPrenotations());
    }



    @Test
    @Transactional
    public void testAddDatePost(){
        assertEquals(dateService.findOne(date.getId()).get().getId(), date.getId());
        dateService.removeOne(date.getId());
        dateService.removeOne(date2.getId());
    }

//    @Test
//    @Transactional
//    public void testGetDateList() throws IOException {
//        List<Date> dateList = dateService.findAll();
//        int counter = 0;
//        for(Date date : dateList){
//            if(date.getDescription().equals("Test1") || date.getDescription().equals("Test2")){
//                counter += 1;
//            }
//        }
//        dateService.removeOne(date.getId());
//        dateService.removeOne(date2.getId());
//        assertEquals(counter, 2);
//    }

    @Test
    @Transactional
    public void testGetDate(){
        assertEquals(dateResource.getDate(date.getId()).get().getId(), date.getId());
        dateService.removeOne(date.getId());
        dateService.removeOne(date2.getId());
    }

    @Test
    @Transactional
    public void testRemove() throws IOException {
        dateService.removeOne(date.getId());
        dateService.removeOne(date2.getId());
        assertEquals(dateService.findOne(date.getId()), Optional.empty());
    }



}
