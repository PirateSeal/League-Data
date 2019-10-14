package fr.intech.leaguedata.server.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.intech.leaguedata.server.interfaces.VersionChecker;
import fr.intech.leaguedata.server.model.Version;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VersionCheckerAPI implements VersionChecker {

    private ClientHttpOkHttp client;
    private ObjectMapper mapper;

    private static Version version;

    public VersionCheckerAPI(ClientHttpOkHttp client, ObjectMapper objectMapper) {
        this.client = client;
        this.mapper = objectMapper;
    }

    private String reqVersion() {
        String s = client.get("https://ddragon.leagueoflegends.com/api/versions.json");
        try {
            String[] versions = mapper.readValue(s, String[].class);
            return versions[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Version getVersion() {

        if (version == null || (version.getLastUpdated() < (System.currentTimeMillis() - 3600000))) {
            version = new Version();
            version.setVersion(reqVersion());
            version.setLastUpdated(System.currentTimeMillis());
        }

        return version;
    }
}
