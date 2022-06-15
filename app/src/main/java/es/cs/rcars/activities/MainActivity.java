package es.cs.rcars.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;

import es.cs.rcars.R;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout btnWide;
    private LinearLayout btnOffices;
    private LinearLayout btnWorkshops;
    private LinearLayout btnVehicles;
    private final String urlRCars ="https://rcarscanarias.com/vehiculos-premium-generales/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initializeViewComponents();
    }

    private void initializeViewComponents() {
        initializeButtons();
    }

    private void initializeButtons() {
        btnWide = findViewById(R.id.itemFinalWide);
        btnOffices = findViewById(R.id.itemOffices);
        btnWorkshops = findViewById(R.id.itemWorkshop);
        btnVehicles = findViewById(R.id.itemVehicles);

        btnWorkshops.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("Show","Workshops");
            startActivity(intent);
        });

        btnVehicles.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VehiclesActivity.class);
            intent.putExtra("Show","Vehicles");
            startActivity(intent);
        });

        btnOffices.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("Show","Offices");
            startActivity(intent);
        });

        btnWide.setOnClickListener(v -> {
            Uri uri = Uri.parse(urlRCars);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });
    }
}