package com.cjgaliana.xamarinvideos.Services;

import android.content.Context;

import com.cjgaliana.xamarinvideos.Models.EvolveSession;
import com.cjgaliana.xamarinvideos.Models.VideoCollection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by camilo on 15/06/2016.
 */
public class DataService {


    private static DataService instance;

    private DataService() {
    }

    public static DataService getInstance() {
        if (instance == null) {
            instance = new DataService();
        }
        return instance;
    }


    public List<VideoCollection> getVideoCollections(Context context) {
        String json = this.readAsset(context, "Collections.json");

        List<VideoCollection> data = new Gson().fromJson(json, new TypeToken<List<VideoCollection>>() {
        }.getType());
        return data;
    }

    public List<EvolveSession> getAllSessions(Context context, String fileName) {
        String json = this.readAsset(context, fileName);

        List<EvolveSession> data = new Gson().fromJson(json, new TypeToken<List<EvolveSession>>() {
        }.getType());
        return data;
    }


    private String readAsset(Context context, String fileName) {
        try {
            Context ctx = context;
            InputStream inputStream = ctx.getAssets().open(fileName);

            if (inputStream != null) {
                //create a buffer that has the same size as the InputStream
                byte[] buffer = new byte[inputStream.available()];
                //read the text file as a stream, into the buffer
                inputStream.read(buffer);
                //create a output stream to write the buffer into
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                //write this buffer to the output stream
                outputStream.write(buffer);
                //Close the Input and Output streams
                outputStream.close();
                inputStream.close();

                //return the output stream as a String
                return outputStream.toString();
            }

            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}


