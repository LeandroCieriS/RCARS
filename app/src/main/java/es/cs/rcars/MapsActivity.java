package es.cs.rcars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MapsActivity extends AppCompatActivity {
    private String show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);
        show = getIntent().getStringExtra("Show");
    }
}