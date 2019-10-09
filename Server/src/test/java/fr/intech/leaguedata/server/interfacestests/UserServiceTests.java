package fr.intech.leaguedata.server.interfacestests;

import fr.intech.leaguedata.server.interfaces.UserService;
import fr.intech.leaguedata.server.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {


    @Autowired
    UserService service;

    @Test
    public void getUserByName() throws IOException {
        String name = "Le Phoque Pirate";
        User user = new User("Le Phoque Pirate");

        User userByName = service.getUserByName(name);

        Assert.assertNotNull(userByName);
        Assert.assertEquals(user.getName(), userByName.getName());
    }

}
