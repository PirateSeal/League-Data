package fr.intech.leaguedata;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import fr.intech.leaguedata.getdata.DataGetter;
import fr.intech.leaguedata.getdata.NewDataListener;
import fr.intech.leaguedata.model.RankedQueue;
import fr.intech.leaguedata.model.User;


public class MainActivity extends Activity {

    AutoCompleteTextView summonerName;
    TextView summonerLvl;
    CircularImageView summonerIcon;
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

        final DataGetter dataGetter = new DataGetter(mapper);

        summonerName = findViewById(R.id.summonerName);
        summonerLvl = findViewById(R.id.summonerLvl);
        summonerIcon = findViewById(R.id.summonerIcon);
        queue = findViewById(R.id.queue);
        rank = findViewById(R.id.rank);
        lpCount = findViewById(R.id.lpCount);
        rankLogo = findViewById(R.id.rankLogo);


        dataGetter.setDataListener(dataListener, serializer);

        summonerIcon.setOnClickListener(v -> {
            String[] urls = urlBuilder.buildUrlName(String.valueOf(summonerName.getText()));
            dataGetter.requestData(urls[0], "user");
            dataGetter.requestData(urls[1], "queues");
        });

    }

    @SuppressLint("SetTextI18n")
    public void refreshUI() {
        try {
            User user = mapper.readValue(serializer.getData("user"), User.class);

            RankedQueue rankedQueue = mapper.readValue(serializer.getData("queues"), RankedQueue.class);

            runOnUiThread(() -> {
                summonerLvl.setText(String.valueOf(user.getSummonerLevel()));
                Picasso.get()
                        .load("http://ddragon.leagueoflegends.com/cdn/9.20.1/img/profileicon/" + user.getProfileIconId() + ".png")
                        .into(summonerIcon);
                queue.setText(rankedQueue.getQueueType());
                rank.setText(rankedQueue.getTier() + " " + rankedQueue.getRank());
                lpCount.setText(String.valueOf(rankedQueue.getLeaguePoints()));

            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
