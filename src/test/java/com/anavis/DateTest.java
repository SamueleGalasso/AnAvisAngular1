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
import java.util.ArrayList;
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
        date.setDescription("Test1");
        date.setRemainingNumber(12);
        date2.setPlace("PDA");
        date2.setPrenotationDate("2020-10-09");
        date2.setActive(true);
        date2.setDescription("Test2");
        date2.setRemainingNumber(10);
        dateResource.addDatePost(date);
        dateResource.addDatePost(date2);
    }



    @Test
    public void testAddDatePost(){
        assertEquals(dateService.findOne(date.getId()).get().getId(), date.getId());
        dateService.removeOne(date.getId());
        dateService.removeOne(date2.getId());
    }

    @Test
    public void testGetDateList(){
        List<Date> dateList = dateResource.getDateList();
        List<Date> dateList1 = new ArrayList<>();
        for(Date date : dateList){
            if(date.getDescription().equals("Test1") || date.getDescription().equals("Test2")){
                dateList1.add(date);
            }
        }
        dateService.removeOne(date.getId());
        dateService.removeOne(date2.getId());
        assertEquals(dateList1.size(), 2);

    }

    @Test
    public void testGetDate(){
        assertEquals(dateResource.getDate(date.getId()).get().getId(), date.getId());
        dateService.removeOne(date.getId());
        dateService.removeOne(date2.getId());
    }

    @Test
    public void testRemove() throws IOException {
        dateResource.remove(date.getId().toString());
        dateResource.remove(date2.getId().toString());
        List<Date> dateList = dateResource.getDateList();
        List<Date> dateList1 = new ArrayList<>();
        for(Date date : dateList){
            if(date.getDescription().equals("Test1") || date.getDescription().equals("Test2")){
                dateList1.add(date);
            }
        }

        assertEquals(dateList1.size(), 0);
    }



}
