package fr.intech.leaguedata.server.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.intech.leaguedata.server.interfaces.UserService;
import fr.intech.leaguedata.server.model.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;

@Service
public class UserServiceImpl implements UserService {

    private ClientHttpOkHttp client;
    private ObjectMapper objectMapper;

    public UserServiceImpl(ClientHttpOkHttp client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public User getUserByName(String name) throws IOException {

        String url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + URLEncoder
                .encode(name, "UTF-8").replace("+","%20");

        String s = client.get(url);
        User user = objectMapper.readValue(s, User.class);

        return user;
    }
}
