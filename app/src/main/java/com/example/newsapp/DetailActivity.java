package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_news);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String type = intent.getStringExtra("type");

        TextView desc = findViewById(R.id.desc);
        TextView titleView = findViewById(R.id.titleView);
        Button send = findViewById(R.id.send);

        desc.setText(description);
        titleView.setText(title);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail(new String[]{"recipient@example.com"}, title, description);
            }
        });
    }
    private void composeEmail(String[] addresses, String subject,String body) {
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent2.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent2.putExtra(Intent.EXTRA_TEXT, body);

        if (intent2.resolveActivity(getPackageManager()) != null) {
            startActivity(intent2);
        }
    }
}
