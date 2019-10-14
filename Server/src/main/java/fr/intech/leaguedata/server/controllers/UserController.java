package fr.intech.leaguedata.server.controllers;

import fr.intech.leaguedata.server.interfaces.ClientHttp;
import fr.intech.leaguedata.server.interfaces.UserService;
import fr.intech.leaguedata.server.model.RankedQueue;
import fr.intech.leaguedata.server.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{name}")
    @Tag(name = "Get user", description = "Using Summoner Name, Get the user's ids")
    public User getUserByName(@PathVariable String name) throws IOException {
        return service.getUserByName(name);
    }

    @GetMapping("/{name}/queues")
    @Tag(name = "Get user queues info", description = "Get all queues info for Summoner Name given")
    public RankedQueue[] getUserQueuesRanks(@PathVariable String name) {
        return service.getUserQueuesInfo(name);
    }

    @GetMapping("/{name}/queue/{queue}")
    @Tag(name = "Get user queue info", description = "Get the queue info for queue and Summoner Name given")
    public RankedQueue getUserQueueRank(@PathVariable String name, @PathVariable String queue) {
        return service.getUserQueueInfo(name, queue);
    }

}
