package fr.intech.leaguedata;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class MainActivity extends Activity {

    TextView summonerName;
    TextView summonerLvl;
    ObjectMapper mapper = new ObjectMapper();
    CircularImageView refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DataRefresher dataRefresher = new DataRefresher(mapper, this);

        summonerName = findViewById(R.id.summonerName);
        summonerLvl = findViewById(R.id.summonerLvl);
        refresh = findViewById(R.id.summonerIcon);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshUI(dataRefresher);
            }
        });

    }

    public void refreshUI(DataRefresher refresher) {
        try {
            String url = "http://tcousin.com:2525/user/";
            url += URLEncoder.encode("Le Phoque Pirate", "UTF-8").replace("+", "%20");
            refresher.refreshData(url);
            FileInputStream fileInputStream = openFileInput("User.json");
            final User user = mapper.readValue(fileInputStream, User.class);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    summonerName.setText(user.getName());
                    summonerLvl.setText("" + user.getSummonerLevel());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
