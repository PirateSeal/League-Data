package fr.intech.leaguedata.server.interfaces;

import fr.intech.leaguedata.server.model.Version;

public interface VersionChecker {

    /**
     * @return League patch version under json format
     */
    public Version getVersion();
}
