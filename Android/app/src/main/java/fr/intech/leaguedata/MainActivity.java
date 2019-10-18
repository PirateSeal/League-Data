package fr.intech.leaguedata;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import fr.intech.leaguedata.getdata.DataSerializer;
import fr.intech.leaguedata.getdata.UrlBuilder;
import fr.intech.leaguedata.getdata.UserInfoRefresher;
import fr.intech.leaguedata.getdata.NewDataListener;
import fr.intech.leaguedata.model.RankedQueue;
import fr.intech.leaguedata.model.User;


public class MainActivity extends Activity {

    AutoCompleteTextView summonerName;
    TextView summonerLvl;
    CircularImageView refreshData;
    TextView queue;
    TextView rank;
    TextView lpCount;
    ImageView rankLogo;

    ObjectMapper mapper = new ObjectMapper();
    DataSerializer serializer = new DataSerializer(mapper, this);
    UrlBuilder urlBuilder = new UrlBuilder();

    NewDataListener dataListener = this::refreshUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UserInfoRefresher userInfoRefresher = new UserInfoRefresher(mapper);

        summonerName = findViewById(R.id.summonerName);
        summonerLvl = findViewById(R.id.summonerLvl);
        refreshData = findViewById(R.id.summonerIcon);
        queue = findViewById(R.id.queue);
        rank = findViewById(R.id.rank);
        lpCount = findViewById(R.id.lpCount);
        rankLogo = findViewById(R.id.rankLogo);


        userInfoRefresher.setDataListener(dataListener, serializer);

        String[] urls = urlBuilder.buildUrlName(String.valueOf(summonerName.getText()));
        refreshData.setOnClickListener(v -> {
            for (String url : urls) userInfoRefresher.refreshData(url);
        });
    }

    public void refreshUI() {
        String data = serializer.getData();
        User user;
        RankedQueue[] queues;


        try {
            user = mapper.readValue(data, User.class);
            runOnUiThread(() -> {
                summonerLvl.setText(String.valueOf(user.getSummonerLevel()));
                Picasso.get()
                        .load("http://ddragon.leagueoflegends.com/cdn/9.20.1/img/profileicon/" + user.getProfileIconId() + ".png")
                        .into(refreshData);
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            try {
                queues = mapper.readValue(data, RankedQueue[].class);

                RankedQueue solo = queues[2];
                RankedQueue flex = queues[1];
                RankedQueue tft = queues[0];

                runOnUiThread(() -> {
                    queue.setText("Solo / Duo");
                    rank.setText(solo.getTier() + " " + solo.getRank());
                    lpCount.setText(String.valueOf(solo.getLeaguePoints()));
                });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }


    }
}
