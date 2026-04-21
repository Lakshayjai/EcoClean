
package com.example.portfolioapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EcoCleanActivity extends AppCompatActivity {

    EditText etAddress;
    TextView tvLocation;
    Button btnGetLocation, btnRequest;

    FusedLocationProviderClient fusedLocationClient;
    double latitude = 0, longitude = 0;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecoclean);

        etAddress = findViewById(R.id.etAddress);
        tvLocation = findViewById(R.id.tvLocation);
        btnGetLocation = findViewById(R.id.btnGetLocation);
        btnRequest = findViewById(R.id.btnRequest);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        db = FirebaseFirestore.getInstance();

        btnGetLocation.setOnClickListener(v -> getLocation());
        btnRequest.setOnClickListener(v -> sendRequest());
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                tvLocation.setText("Lat: " + latitude + " , Lng: " + longitude);
            } else {
                tvLocation.setText("Unable to fetch location");
            }
        });
    }

    private void sendRequest() {
        String address = etAddress.getText().toString();

        if (address.isEmpty()) {
            Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> request = new HashMap<>();
        request.put("address", address);
        request.put("latitude", latitude);
        request.put("longitude", longitude);
        request.put("status", "pending");
}
