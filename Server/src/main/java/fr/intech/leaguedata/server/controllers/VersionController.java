package fr.intech.leaguedata.server.controllers;

import fr.intech.leaguedata.server.interfaces.VersionChecker;
import fr.intech.leaguedata.server.model.Version;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class VersionController {

    private VersionChecker service;

    public VersionController(VersionChecker service) {
        this.service = service;
    }

    @GetMapping
    @Tag(name = "Patch version", description = "Get actual Lol Patch version")
    public Version getVersion() {
        return service.getVersion();
    }

}
