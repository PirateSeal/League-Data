package fr.intech.leaguedata.server.interfacestests;

import fr.intech.leaguedata.server.implementations.ClientHttpOkHttp;
import fr.intech.leaguedata.server.interfaces.UserService;
import fr.intech.leaguedata.server.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService service;

    @MockBean
    ClientHttpOkHttp fauxClient;

    @Test
    public void getUserByName() throws IOException {
        Mockito.when(fauxClient.get(Mockito.anyString())).thenReturn("{\n" +
                "\"id\": \"_tEBhnSPk2cxQrWrLiAK3pHKlciQR_d2BHLL7XlO6Xy_-sA\",\n" +
                "\"accountId\": \"jR-slhrrMtwwj8WzvhvQeKXdB4z5xXyrrTY1CC4vEU4tVw\",\n" +
                "\"puuid\": \"msE19_hRasZf0KthEBFnnWv8fj3VpeDvUE2N6IjAnBhohGIqGjXlmbWEbGjOFgnYGxVMIKjya7nrjA\",\n" +
                "\"name\": \"Le Phoque Pirate\",\n" +
                "\"profileIconId\": 3779,\n" +
                "\"revisionDate\": 1570372661000,\n" +
                "\"summonerLevel\": 80\n" +
                "}");
        String str = fauxClient.get(":)");
        String name = "Le Phoque Pirate";
        User user = new User("Le Phoque Pirate");

        User userByName = service.getUserByName(name);

        Assert.assertNotNull(userByName);
        Assert.assertEquals(user.getName(), userByName.getName());
    }

}
