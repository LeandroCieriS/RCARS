package es.cs.rcars.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import es.cs.rcars.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private String show;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);
        show = getIntent().getStringExtra("Show");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        LatLng t1 = new LatLng(28.5001631687059, -13.862253480109757);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(t1, 17));
        //MarkerOptions marker = new MarkerOptions().title("Taller").position(t1).icon(BitmapDescriptorFactory.fromResource(R.drawable.workshop));
        MarkerOptions marker = new MarkerOptions().position(t1);

        map.addMarker(marker);
    }
}