package com.isw.discacar.activities.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.isw.discacar.R;
import com.isw.discacar.activities.MainActivity;
import com.isw.discacar.activities.client.MapClientActivity;
import com.isw.discacar.providers.AuthProvider;

public class MapDriverActivity extends AppCompatActivity implements OnMapReadyCallback {

    Button mButtonLogout;
    AuthProvider mAuthProvider;

    private  GoogleMap mMap;
    private SupportMapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_driver);

        mButtonLogout = findViewById(R.id.btnLogout);
        mAuthProvider = new AuthProvider();

        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuthProvider.logout();
                Intent intent = new Intent(MapDriverActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                new CameraPosition.Builder()
                        .target(new LatLng(4.828870,-74.354904))
                        .zoom(18f)
                        .build()
        ));

    }
}