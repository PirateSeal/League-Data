package fr.intech.leaguedata.server.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.intech.leaguedata.server.interfaces.UserService;
import fr.intech.leaguedata.server.model.RankedQueue;
import fr.intech.leaguedata.server.model.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private ClientHttpOkHttp client;
    private ObjectMapper objectMapper;

    public UserServiceImpl(ClientHttpOkHttp client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public User getUserByName(String name) {

        try {
            String url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + URLEncoder
                    .encode(name, "UTF-8").replace("+", "%20");
            String s = client.get(url);
            User user = objectMapper.readValue(s, User.class);
            return user;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public RankedQueue getUserQueueInfo(String name, String queue) {
        try {
            User user = getUserByName(name);
            String url = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + user.getId();

            String s = client.get(url);
            RankedQueue[] queues = objectMapper.readValue(s, RankedQueue[].class);

            for (RankedQueue rankedQueue : queues) {
                if (rankedQueue.getQueueType() == name) return rankedQueue;
            }

            return null;

        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public RankedQueue[] getUserQueuesInfo(String name) {
        try {
            User user = getUserByName(name);
            String url = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + user.getId();

            String s = client.get(url);
            RankedQueue[] queues = objectMapper.readValue(s, RankedQueue[].class);

            return queues;

        } catch (IOException e) {
            return null;
        }
    }
}
