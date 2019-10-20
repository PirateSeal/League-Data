package fr.intech.leaguedata.getdata;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import fr.intech.leaguedata.model.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataGetter {

    private ObjectMapper mapper;
    private OkHttpClient client;
    private DataSerializer serializer;

    public void setDataListener(NewDataListener dataListener, DataSerializer serializer) {
        this.dataListener = dataListener;
        this.serializer = serializer;
    }

    private NewDataListener dataListener;


    public DataGetter(ObjectMapper mapper) {
        this.mapper = mapper;
        this.client = new OkHttpClient()
                .newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    public void requestData(String url, String typeOfObject) {
        Request request = new Request.Builder().url(url).get().build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e("DataRefresh", "Erreur de chargement", e);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws
                            IOException {

                        String s = Objects.requireNonNull(response.body()).string();

                        serializer.saveData(s,typeOfObject);

                        if(dataListener != null) dataListener.onNewData();
                    }
                });

    }


}
