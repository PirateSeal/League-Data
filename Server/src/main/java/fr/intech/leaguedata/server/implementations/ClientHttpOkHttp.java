package fr.intech.leaguedata.server.implementations;

import fr.intech.leaguedata.server.interfaces.ClientHttp;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClientHttpOkHttp implements ClientHttp {


    private OkHttpClient client;

    public ClientHttpOkHttp(OkHttpClient client) {
        this.client = client;
    }


    @Override
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url).header("X-Riot-Token","RGAPI-d2a75347-bed5-474f-b2d1-074c0b0121f0")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}

