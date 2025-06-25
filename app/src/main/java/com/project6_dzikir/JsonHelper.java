package com.project6_dzikir;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("dzikir.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public List<Dzikir> loadDzikir(int type) {
        List<Dzikir> list = new ArrayList<>();
        try {
            String json = loadJSONFromAsset();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray dzikirArray;
            if (type == 0) {
                dzikirArray = jsonObject.getJSONArray("dzikir_pagi");
            } else {
                dzikirArray = jsonObject.getJSONArray("dzikir_petang");
            }

            for (int i = 0; i < dzikirArray.length(); i++) {
                JSONObject obj = dzikirArray.getJSONObject(i);
                Dzikir dzikir = new Dzikir(
                        obj.getString("arab"),
                        obj.getString("latin"),
                        obj.getString("terjemah"),
                        obj.getInt("jumlah"),
                        obj.getString("audio")
                );
                list.add(dzikir);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
