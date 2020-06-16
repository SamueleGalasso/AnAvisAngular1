package com.anavis;

import com.anavis.domain.Date;
import com.anavis.resource.DateResource;
import com.anavis.service.DateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

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
        date.setDescription("Test Description");
        date.setRemainingNumber(12);
        date2.setPlace("PDA");
        date2.setPrenotationDate("2020-10-09");
        date2.setActive(true);
        date2.setDescription("Test Description 2");
        date2.setRemainingNumber(10);
        dateResource.addPrenotationPost(date);
        dateResource.addPrenotationPost(date2);
    }



    @Test
    public void testAddPrenotationPost(){
        assertEquals(dateService.findOne(date.getId()).get().getId(), date.getId());
        dateService.removeOne(date.getId());
        dateService.removeOne(date2.getId());
    }

    @Test
    public void testGetPrenotationList(){
        List<Date> dateList = dateResource.getPrenotationList();
        assertEquals(dateList.get(0).getId(), date.getId());
        assertEquals(dateList.get(1).getId(), date2.getId());
        dateService.removeOne(date.getId());
        dateService.removeOne(date2.getId());
    }

    @Test
    public void testGetPrenotation(){
        assertEquals(dateResource.getPrenotation(date.getId()).get().getId(), date.getId());
        dateService.removeOne(date.getId());
        dateService.removeOne(date2.getId());
    }

    @Test
    public void testRemove() throws IOException {
        dateResource.remove(date.getId().toString());
        assertEquals(dateResource.getPrenotationList().size(), 1);
        dateService.removeOne(date2.getId());
    }



}
