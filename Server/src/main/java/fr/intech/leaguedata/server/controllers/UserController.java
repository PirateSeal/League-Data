package fr.intech.leaguedata.server.controllers;

import fr.intech.leaguedata.server.interfaces.ClientHttp;
import fr.intech.leaguedata.server.interfaces.UserService;
import fr.intech.leaguedata.server.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    public UserController(UserService service, ClientHttp client) {
        this.service = service;
    }

    @GetMapping("/{name}")
    @Tag(name = "Get user", description = "Using Summoner Name, Get the user's ids")
    public User getUserByName(@PathVariable String name) throws IOException {
        return service.getUserByName(name);
    }

}
