package com.example.newsapp ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<News> newsList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("news_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String image = jsonObject.getString("image");
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                String type = jsonObject.getString("type");
                String date = jsonObject.getString("date");
                newsList.add(new News(image, title, description,type, date));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        NewsAdapter adapter = new NewsAdapter(this,newsList);
        recyclerView.setAdapter(adapter);
    }
}