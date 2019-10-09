package fr.intech.leaguedata.server.interfaces;

import java.io.IOException;

public interface ClientHttp {

    public String get(String url) throws IOException;

}
