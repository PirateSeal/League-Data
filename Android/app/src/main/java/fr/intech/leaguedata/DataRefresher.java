package fr.intech.leaguedata;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class DataRefresher {

    ObjectMapper mapper;
    Context context;
    HttpClient client;


    public DataRefresher(ObjectMapper mapper, Context mainActivity) {
        this.mapper = mapper;
        this.client = new HttpClient();
        this.context = mainActivity;
    }

    public void refreshData(String url) {
        Request request = new Request.Builder().url(url).get().build();

        client.newCall(request)
                .enqueue(
                        new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                Log.e("DataRefresh", "Erreur de chargement", e);
                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws
                                    IOException {

                                String s = response.body().string();
                                final User user = mapper.readValue(s, User.class);
                                saveData(user);

                            }
                        });

    }


    public void saveData(User user) {
        try {
            mapper.writeValue(context.openFileOutput("User.json", MODE_PRIVATE), user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
