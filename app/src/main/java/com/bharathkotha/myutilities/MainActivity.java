package com.bharathkotha.myutilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton ibWhatsapp = findViewById(R.id.whatsapp_location_image);
        ibWhatsapp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("MainActivity: ", "Whatsapp button clicked");
                Intent intent = new Intent(MainActivity.this, WhatsappShareActivity.class);
                startActivity(intent);
            }
        });

        TextView textView = findViewById(R.id.smsText);
        SharedPreferences sp = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        String message = sp.getString("Message", "No message received");
        textView.setText(message);
    }
}