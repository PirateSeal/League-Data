package fr.intech.leaguedata;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    ProgressBar spinner;

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
        spinner = findViewById(R.id.spinner);

        rankLogo.setVisibility(View.GONE);

        dataGetter.setDataListener(dataListener, serializer);

        summonerIcon.setOnClickListener(v -> {
            rankLogo.setVisibility(View.GONE);
            spinner.setVisibility(View.VISIBLE);
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

                String imgRank = rankedQueue.getTier().toLowerCase();

                switch (imgRank) {
                    case "bronze":
                        rankLogo.setImageResource(R.drawable.bronze);
                        break;
                    case "silver":
                        rankLogo.setImageResource(R.drawable.silver);
                        break;
                    case "gold":
                        rankLogo.setImageResource(R.drawable.gold);
                        break;
                    case "platinum":
                        rankLogo.setImageResource(R.drawable.platinum);
                        break;
                    case "diamond":
                        rankLogo.setImageResource(R.drawable.diamond);
                        break;
                    case "master":
                        rankLogo.setImageResource(R.drawable.master);
                        break;
                    case "grandmaster":
                        rankLogo.setImageResource(R.drawable.grandmaster);
                        break;
                    case "challenger":
                        rankLogo.setImageResource(R.drawable.challenger);
                        break;
                    default:
                        rankLogo.setImageResource(R.drawable.unranked);
                        break;
                }

                rankLogo.setVisibility(View.VISIBLE);

            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
