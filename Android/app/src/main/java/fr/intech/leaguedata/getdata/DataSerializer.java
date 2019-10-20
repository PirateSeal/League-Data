package fr.intech.leaguedata.getdata;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

public class DataSerializer {

    private ObjectMapper mapper;
    private Context context;

    public DataSerializer(ObjectMapper mapper, Context context) {
        this.mapper = mapper;
        this.context = context;
    }

    void saveData(String s, String location) {
        try {
            FileOutputStream stream = context.openFileOutput(location + ".json", MODE_PRIVATE);
            mapper.writeValue(stream, s);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(String location) {
        FileInputStream stream;
        try {
            stream = context.openFileInput(location + ".json");
            return mapper.readValue(stream, String.class);
        } catch (IOException e) {
            return null;
        }
    }
}
