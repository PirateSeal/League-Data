package fr.intech.leaguedata.server.implementations;

import fr.intech.leaguedata.server.interfaces.ClientHttp;
import okhttp3.*;
import org.jboss.logging.Property;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClientHttpOkHttp implements ClientHttp {


    private OkHttpClient client;

    @Value("${lol.api.key}")
    private String apiKey;

    public ClientHttpOkHttp(OkHttpClient client) {
        this.client = client;
    }


    @Override
    public String get(String url) {
        System.out.println("Clé : "+apiKey);
        Request request = new Request.Builder()
                .url(url).header("X-Riot-Token", apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}

