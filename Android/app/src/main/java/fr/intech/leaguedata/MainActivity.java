package fr.intech.leaguedata;

import android.app.Activity;
import android.bluetooth.BluetoothClass;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static java.lang.Thread.sleep;


public class MainActivity extends Activity {

    AutoCompleteTextView summonerName;
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

    public void refreshUI(final DataRefresher refresher) {
        try {
            String url = "http://tcousin.com:2525/user/";
            url += URLEncoder.encode(String.valueOf(summonerName.getText()), "UTF-8").replace("+", "%20");

            refresher.refreshData(url);

            FileInputStream stream = openFileInput("temp.json");

            final User user = mapper.readValue(stream, User.class);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    summonerName.setText(user.getName());
                    summonerLvl.setText(String.valueOf(user.getSummonerLevel()));
                    Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/9.20.1/img/profileicon/"+user.getProfileIconId()+".png");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
