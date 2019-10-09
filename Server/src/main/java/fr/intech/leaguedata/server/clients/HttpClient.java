package fr.intech.leaguedata.server.clients;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Configuration
public class HttpClient {

    @Bean
    public okhttp3.OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().callTimeout(1000, TimeUnit.MILLISECONDS).build();
        return client;
    }
}
