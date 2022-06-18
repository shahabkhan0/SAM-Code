package com.vc.technologies.sam.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vc.technologies.sam.R;

import java.util.Map;

public class TrackingActivity extends AppCompatActivity implements OnMapReadyCallback {
    private DatabaseReference mDatabase;
    private GoogleMap mMap;
    private static final String TAG = "Maps";
    float latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
//        getSupportActionBar().setTitle("Tracking");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("location").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Float> dataMap = (Map) snapshot.getValue();
                Number latitudeN = (Number) dataMap.get("latitude");
                Number longitudeN = (Number) dataMap.get("longitude");
                latitude = latitudeN.floatValue();
                longitude = longitudeN.floatValue();
                Log.v(TAG, Float.toString(latitude) + ',' + Float.toString(longitude));
                LatLng sydney = new LatLng(latitude, longitude);
                mMap = googleMap;
                mMap.addMarker(new MarkerOptions()
                        .position(sydney)
                        .title("Marker in Islamabad"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}