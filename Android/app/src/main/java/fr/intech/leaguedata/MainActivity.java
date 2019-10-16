package fr.intech.leaguedata;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class MainActivity extends Activity {

    TextView summonerName;
    TextView summonerLvl;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataRefresher dataRefresher = new DataRefresher(mapper, this);

        summonerName = findViewById(R.id.summonerName);
        summonerLvl = findViewById(R.id.summonerLvl);

        String url = "https://tcousin.com:8080/user/";
        try {
            url += URLEncoder.encode("Le Phoque Pirate", "UTF-8").replace("+", "%20");
            dataRefresher.refreshData(url);
            refreshUI();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void refreshUI() {
        try {
            final User user = mapper.readValue(openFileInput("User.json"), User.class);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    summonerName.setText(user.getName());
                    summonerLvl.setText(user.getSummonerLevel());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
