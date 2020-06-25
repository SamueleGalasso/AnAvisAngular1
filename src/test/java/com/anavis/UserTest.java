package com.anavis;

import com.anavis.domain.User;
import com.anavis.resource.UserResource;
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
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserResource userResource;

    private User user;

    @Before
    public void set(){
        this.user = new User();
        this.user.setUsername("pippo");
        userService.save(this.user);
    }

    @Test
    public void getUser(){
        assertEquals(userResource.getUser(this.user.getId()).get().getUsername(), "pippo");
        userService.remove(this.user.getId());
    }

}
