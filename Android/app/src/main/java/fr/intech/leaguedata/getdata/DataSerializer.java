package fr.intech.leaguedata.getdata;

import android.content.Context;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.intech.leaguedata.model.RankedQueue;
import fr.intech.leaguedata.model.User;

import static android.content.Context.MODE_PRIVATE;

public class DataSerializer {

    private ObjectMapper mapper;
    private Context context;

    public DataSerializer(ObjectMapper mapper, Context context) {
        this.mapper = mapper;
        this.context = context;
    }

    void saveData(String s) {
        try {
            FileOutputStream stream = context.openFileOutput("temp.json", MODE_PRIVATE);
            mapper.writeValue(stream, s);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData() {
        FileInputStream stream;
        try {
            stream = context.openFileInput("temp.json");
            return mapper.readValue(stream, String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
