package com.bharathkotha.myutilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

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
    }
}