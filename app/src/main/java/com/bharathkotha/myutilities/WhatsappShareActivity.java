package com.bharathkotha.myutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WhatsappShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_share);
        final EditText etPhoneNumber = findViewById(R.id.whatsapp_phone);

        Button bSendMessage = findViewById(R.id.whatsapp_send_message);
        bSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = etPhoneNumber.getText().toString();
                String url = "https://api.whatsapp.com/send?phone=+91" + phoneNumber;
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setPackage("com.whatsapp");
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        Button bsendLocation = findViewById(R.id.whatsapp_send_location);
        bsendLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location location = new Location("0,0");
                try {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
                catch (SecurityException e) {
                    Toast.makeText(
                            getApplicationContext(),
                            "You need to grant location permission to share location with whatsapp",
                            Toast.LENGTH_LONG
                    ).show();

                }

                String maps_url = "https://www.google.com/maps/search/?api=1%26query=" + location.getLatitude() + "," + location.getLongitude();
                Log.i("WhatsappShareActivity: ", "maps_url: " + maps_url);

                String phoneNumber = etPhoneNumber.getText().toString();
                String whatsapp_url = "https://api.whatsapp.com/send?phone=+91" + phoneNumber + "&text=" + Uri.parse(maps_url);

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setPackage("com.whatsapp");
                intent.setData(Uri.parse(whatsapp_url));
                startActivity(intent);

            }
        });
    }
}