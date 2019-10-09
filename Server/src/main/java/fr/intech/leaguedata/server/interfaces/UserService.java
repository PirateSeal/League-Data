package fr.intech.leaguedata.server.interfaces;

import fr.intech.leaguedata.server.model.User;

import java.io.IOException;

public interface UserService {
    public User getUserByName(String name) throws IOException;

}
